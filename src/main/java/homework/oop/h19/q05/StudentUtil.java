package homework.oop.h19.q05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class StudentUtil {
    public List<String> sort(String filePath) {
        try {
            List<String> res = new LinkedList<>();
            HashMap<String,Integer> totalScore = new HashMap<>();
            HashMap<String,Integer> chineseScore = new HashMap<>();
            HashMap<String,Integer> mathScore = new HashMap<>();
            HashMap<String,Integer> englishScore = new HashMap<>();

            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String curLine;
            while ((curLine = br.readLine()) != null) {
                String[] split = curLine.split(",");
                for (int i = 0; i < 3; i++) {
                    if (i == 2) {
                        split[i] = split[i].trim().substring(0, split[i].length() - 1);
                    }else{
                        split[i] = split[i].trim();
                    }
                }
                // 加上总分
                totalScore.put(split[0], totalScore.getOrDefault(split[0], 0) + Integer.parseInt(split[2]));
                // 语文数学英语防止空指针异常 先记一个0
                chineseScore.put(split[0], chineseScore.getOrDefault(split[0], 0) + Integer.parseInt(split[2]));
                mathScore.put(split[0], mathScore.getOrDefault(split[0], 0) + Integer.parseInt(split[2]));
                englishScore.put(split[0], englishScore.getOrDefault(split[0], 0) + Integer.parseInt(split[2]));
                // 如果是语数英 记录语数英分数
                if (split[1].equals("语文")) {
                    chineseScore.put(split[0], totalScore.getOrDefault(split[0], 0) + Integer.parseInt(split[2]));
                } else if (split[1].equals("数学")) {
                    mathScore.put(split[0], totalScore.getOrDefault(split[0], 0) + Integer.parseInt(split[2]));
                } else if (split[1].equals("英语")) {
                    englishScore.put(split[0], totalScore.getOrDefault(split[0], 0) + Integer.parseInt(split[2]));
                }
            }
            // 开始排名
            Set<Map.Entry<String, Integer>> entries = totalScore.entrySet();

            // 倒序排列成绩 然后逐个放置
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((o1, o2) -> {
                int i = o2.getValue().compareTo(o1.getValue());
                if (i == 0) {// 总成绩相同时 比较语文
                    int i1 = chineseScore.get(o2.getKey()).compareTo(chineseScore.get(o2.getKey()));
                    if (i1 == 0) {// 语文相同时比较数学
                        int i2 = mathScore.get(o2.getKey()).compareTo(mathScore.get(o2.getKey()));
                        if (i2 == 0) {// 数学相同时比较英语
                            return englishScore.get(o2.getKey()).compareTo(englishScore.get(o2.getKey()));
                        }else{
                            return i2;
                        }
                    } else {
                        return i1;
                    }
                }else{
                    return i;
                }
            });
            pq.addAll(entries);
            while (!pq.isEmpty()) {
                Map.Entry<String, Integer> entry = pq.poll();
                res.add(entry.getKey());
            }
            return res;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
