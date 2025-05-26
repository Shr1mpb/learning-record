package homework.oop.h17;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BattleField {
    private static List<Player> players = new ArrayList<>();
    private static List<GameBase> gameBases = new ArrayList<>();

    public static void init(String path) throws IOException {
        Properties prop = new Properties();
//        FileInputStream fileInputStream = new FileInputStream(path);
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            prop.load(fileInputStream);
            setProperties(prop, "base");
            setProperties(prop, "heavyTank");
            setProperties(prop, "mediumTank");
            setProperties(prop, "rifleSoldier");
            setProperties(prop, "RPGSoldier");
            setProperties(prop, "dog");
            setProperties(prop, "barrack");
            setProperties(prop, "warFactory");
        }catch (Exception e) {
            throw new RuntimeException("读取配置文件失败！");
        }

    }
    private static void setProperties(Properties properties, String unitType) {
        // 读取
        int health = Integer.parseInt(properties.getProperty(unitType + ".health").trim());
        int range = Integer.parseInt(properties.getProperty(unitType + ".range").trim());
        int strength = Integer.parseInt(properties.getProperty(unitType + ".strength").trim());
        // 设置
        switch (unitType) {
            case "base":
                PropLoadConfig.setBaseHealth(health);
                PropLoadConfig.setBaseRange(range);
                PropLoadConfig.setBaseStrength(strength);
                break;
            case "heavyTank":
                PropLoadConfig.setHeavyTankHealth(health);
                PropLoadConfig.setHeavyTankRange(range);
                PropLoadConfig.setHeavyTankStrength(strength);
                break;
            case "mediumTank":
                PropLoadConfig.setMediumTankHealth(health);
                PropLoadConfig.setMediumTankRange(range);
                PropLoadConfig.setMediumTankStrength(strength);
                break;
            case "rifleSoldier":
                PropLoadConfig.setRifleSoldierHealth(health);
                PropLoadConfig.setRifleSoldierRange(range);
                PropLoadConfig.setRifleSoldierStrength(strength);
                break;
            case "RPGSoldier":
                PropLoadConfig.setRpgSoldierHealth(health);
                PropLoadConfig.setRpgSoldierRange(range);
                PropLoadConfig.setRpgSoldierStrength(strength);
                break;
            case "dog":
                PropLoadConfig.setDogHealth(health);
                PropLoadConfig.setDogRange(range);
                PropLoadConfig.setDogStrength(strength);
                break;
            case "barrack":
                PropLoadConfig.setBarrackHealth(health);
                PropLoadConfig.setBarrackRange(range);
                PropLoadConfig.setBarrackStrength(strength);
                break;
            case "warFactory":
                PropLoadConfig.setWarFactoryHealth(health);
                PropLoadConfig.setWarFactoryRange(range);
                PropLoadConfig.setWarFactoryStrength(strength);
                break;
        }

    }

    public static void createPlayer(String playerName) {
        players.add(new Player(playerName));
    }

    public static GameBase createGameBase(Player player, int x, int y) {
        return GameBase.createGameBase(x, y, player);
    }

    public static List<Player> getAllPlayer() {
        return players;
    }

    public static List<GameBase> getGameBases() {
        return gameBases;
    }
}
