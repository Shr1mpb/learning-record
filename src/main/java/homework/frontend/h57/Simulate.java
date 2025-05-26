package homework.frontend.h57;

import java.util.*;

public class Simulate {
    private final Random random = new Random();

    public SimResult sim(Param param, int days) {
        // 初始化居民状态
        Person[] people = initializePeople(param);

        // 初始化医院队列
        Hospital hospital = new Hospital(param.getHospitalSize());

        // 初始化家庭和公司
        Family[] families = initializeFamilies(param, people);
        Company[] companies = initializeCompanies(param, people);

        // 初始化免疫人群
        initializeImmunizations(param, people);

        // 设置初始病人
        initializePatients(param, people);

        // 开始模拟每一天
        for (int day = 0; day < days; day++) {
            // 白天：在公司传播
            simulateDaytime(people, companies, param);

            // 晚上：在家庭传播
            simulateNighttime(people, families, param);

            // 处理潜伏期结束的病人
            processLatentPeriodEnd(people, hospital);

            // 处理医院中的病人
            processHospitalPatients(people, hospital, param);

            // 处理在家中的病人
            processHomePatients(people, param);
        }

        // 收集结果
        return collectResults(people);
    }

    private Person[] initializePeople(Param param) {
        Person[] people = new Person[param.getCityPopulation()];
        for (int i = 0; i < people.length; i++) {
            people[i] = new Person(i);
        }
        return people;
    }

    private Family[] initializeFamilies(Param param, Person[] people) {
        int familyCount = param.getCityPopulation() / param.getFamilySize();
        Family[] families = new Family[familyCount];

        for (int i = 0; i < familyCount; i++) {
            int start = i * param.getFamilySize();
            int end = start + param.getFamilySize();
            Person[] familyMembers = Arrays.copyOfRange(people, start, end);
            families[i] = new Family(familyMembers);
        }

        return families;
    }

    private Company[] initializeCompanies(Param param, Person[] people) {
        int companyCount = param.getCityPopulation() / param.getCompanySize();
        Company[] companies = new Company[companyCount];

        // 随机分配公司
        List<Person> shuffled = new ArrayList<>(Arrays.asList(people));
        Collections.shuffle(shuffled);

        for (int i = 0; i < companyCount; i++) {
            int start = i * param.getCompanySize();
            int end = start + param.getCompanySize();
            Person[] companyMembers = shuffled.subList(start, end).toArray(new Person[0]);
            companies[i] = new Company(companyMembers);
        }

        return companies;
    }

    private void initializeImmunizations(Param param, Person[] people) {
        int immunizedCount = (int) (param.getCityPopulation() * param.getImmuRate());
        for (int i = 0; i < immunizedCount; i++) {
            people[i].setImmunized(true);
        }
        // 随机打乱免疫人群
        Collections.shuffle(Arrays.asList(people));
    }

    private void initializePatients(Param param, Person[] people) {
        for (int patientId : param.getInitPatients()) {
            if (patientId < people.length) {
                people[patientId].setStatus(Status.LATENT);
                people[patientId].setDaysInStatus(0);
            }
        }
    }

    private void simulateDaytime(Person[] people, Company[] companies, Param param) {
        for (Company company : companies) {
            // 检查公司是否有病人
            boolean hasPatient = company.hasPatient();
            if (!hasPatient) continue;

            // 传播给健康同事
            for (Person person : company.getMembers()) {
                if (person.getStatus() == Status.HEALTHY) {
                    double spreadRate = person.isImmunized() ?
                            param.getSpreadRateCompany() * (1 - param.getImmuEffect()) :
                            param.getSpreadRateCompany();

                    if (random.nextDouble() < spreadRate) {
                        person.setStatus(Status.LATENT);
                        person.setDaysInStatus(0);
                    }
                }
            }
        }
    }

    private void simulateNighttime(Person[] people, Family[] families, Param param) {
        for (Family family : families) {
            // 检查家庭是否有病人
            boolean hasPatient = family.hasPatient();
            if (!hasPatient) continue;

            // 传播给健康家庭成员
            for (Person person : family.getMembers()) {
                if (person.getStatus() == Status.HEALTHY) {
                    double spreadRate = person.isImmunized() ?
                            param.getSpreadRateFamily() * (1 - param.getImmuEffect()) :
                            param.getSpreadRateFamily();

                    if (random.nextDouble() < spreadRate) {
                        person.setStatus(Status.LATENT);
                        person.setDaysInStatus(0);
                    }
                }
            }
        }

        // 更新所有人的状态天数
        for (Person person : people) {
            if (person.getStatus() == Status.LATENT ||
                    person.getStatus() == Status.PATIENT_HOME ||
                    person.getStatus() == Status.PATIENT_HOSPITAL) {
                person.incrementDaysInStatus();
            }
        }
    }

    private void processLatentPeriodEnd(Person[] people, Hospital hospital) {
        for (Person person : people) {
            if (person.getStatus() == Status.LATENT &&
                    person.getDaysInStatus() >= person.getLatentPeriod()) {
                person.setStatus(Status.PATIENT_HOME);
                person.setDaysInStatus(0);
                hospital.addToQueue(person);
            }
        }
    }

    private void processHospitalPatients(Person[] people, Hospital hospital, Param param) {
        // 处理医院中的病人
        Iterator<Person> iterator = hospital.getHospitalizedPatients().iterator();
        while (iterator.hasNext()) {
            Person patient = iterator.next();
            if (random.nextDouble() < param.getHealingRateHospital()) {
                patient.setStatus(Status.CURED);
                iterator.remove(); // 使用迭代器的remove方法
            } else if (random.nextDouble() < param.getDeathRateHospital()) {
                patient.setStatus(Status.DEAD);
                iterator.remove(); // 使用迭代器的remove方法
            }
        }

        // 尝试将排队病人安排到医院
        hospital.assignBeds();
    }

    private void processHomePatients(Person[] people, Param param) {
        for (Person person : people) {
            if (person.getStatus() == Status.PATIENT_HOME) {
                if (random.nextDouble() < param.getHealingRateHome()) {
                    person.setStatus(Status.CURED);
                } else if (random.nextDouble() < param.getDeathRateHome()) {
                    person.setStatus(Status.DEAD);
                }
            }
        }
    }

    private SimResult collectResults(Person[] people) {
        SimResult result = new SimResult();
        int deaths = 0;
        int cured = 0;
        int patients = 0;
        int latents = 0;

        for (Person person : people) {
            switch (person.getStatus()) {
                case DEAD:
                    deaths++;
                    break;
                case CURED:
                    cured++;
                    break;
                case PATIENT_HOME:
                case PATIENT_HOSPITAL:
                    patients++;
                    break;
                case LATENT:
                    latents++;
                    break;
                default:
                    break;
            }
        }

        result.setDeaths(deaths);
        result.setCured(cured);
        result.setPatients(patients);
        result.setLatents(latents);

        return result;
    }
}

// 辅助类
class Person {
    private final int id;
    private Status status = Status.HEALTHY;
    private int daysInStatus = 0;
    private boolean immunized = false;
    private int latentPeriod = 7; // 默认潜伏期

    public Person(int id) {
        this.id = id;
    }

    // getters and setters
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public int getDaysInStatus() { return daysInStatus; }
    public void setDaysInStatus(int days) { this.daysInStatus = days; }
    public void incrementDaysInStatus() { this.daysInStatus++; }
    public boolean isImmunized() { return immunized; }
    public void setImmunized(boolean immunized) { this.immunized = immunized; }
    public int getLatentPeriod() { return latentPeriod; }
    public void setLatentPeriod(int latentPeriod) { this.latentPeriod = latentPeriod; }
    public int getId() { return id; }
}

enum Status {
    HEALTHY, LATENT, PATIENT_HOME, PATIENT_HOSPITAL, CURED, DEAD
}

class Family {
    private final Person[] members;

    public Family(Person[] members) {
        this.members = members;
    }

    public Person[] getMembers() { return members; }

    public boolean hasPatient() {
        for (Person member : members) {
            if (member.getStatus() == Status.LATENT ||
                    member.getStatus() == Status.PATIENT_HOME ||
                    member.getStatus() == Status.PATIENT_HOSPITAL) {
                return true;
            }
        }
        return false;
    }
}

class Company {
    private final Person[] members;

    public Company(Person[] members) {
        this.members = members;
    }

    public Person[] getMembers() { return members; }

    public boolean hasPatient() {
        for (Person member : members) {
            if (member.getStatus() == Status.LATENT ||
                    member.getStatus() == Status.PATIENT_HOME ||
                    member.getStatus() == Status.PATIENT_HOSPITAL) {
                return true;
            }
        }
        return false;
    }
}

class Hospital {
    private final int capacity;
    private final Queue<Person> waitingQueue = new LinkedList<>();
    private final Set<Person> hospitalizedPatients = new HashSet<>();

    public Hospital(int capacity) {
        this.capacity = capacity;
    }

    public void addToQueue(Person person) {
        waitingQueue.add(person);
    }

    public void assignBeds() {
        while (hospitalizedPatients.size() < capacity && !waitingQueue.isEmpty()) {
            Person patient = waitingQueue.poll();
            patient.setStatus(Status.PATIENT_HOSPITAL);
            hospitalizedPatients.add(patient);
        }
    }

    public void releaseBed(Person patient) {
        hospitalizedPatients.remove(patient);
    }

    public Set<Person> getHospitalizedPatients() {
        return hospitalizedPatients;
    }
}