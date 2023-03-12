import org.yaml.snakeyaml.Yaml;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DataHub {
    //variables to read data
    int[][] countBounds, chargeBounds, lifetimeBounds, chargeTimeBounds, taskCountBounds, taskLengthBounds;
    int shift1mins, shift2mins;
    int[] stationBounds, employeeBounds;
    ArrayList<String> nameList;
    //variables to produce data
    int[] counts, taskCounts;
    ArrayList<Integer>[] initialCharges, lifetimes, chargeTimes, taskLengths;
    int workingHours, numStations, numWorkers;
    String[] names;
    Random random;

    public DataHub() throws IOException {
        counts = new int[3];
        initialCharges = new ArrayList[3];
        lifetimes = new ArrayList[3];
        chargeTimes = new ArrayList[3];
        workingHours = 0;
        taskCounts = new int[3];
        taskLengths = new ArrayList[3];
        numStations = 0;
        numWorkers = 0;
        names = new String[3];
        getData();
        produceData();
        random = new Random();
    }

    public void getData() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("Config.yaml"));
        Yaml yaml = new Yaml();
        HashMap<String, Object> data = yaml.load(inputStream);
        nameList = (ArrayList<String>) data.get("types");
        HashMap<String, HashMap<String, Integer>> equipmentData = (HashMap<String, HashMap<String, Integer>>) data.get("equipmentConfig");
        countBounds = new int[3][2];
        countBounds[0][0] = equipmentData.get("equipment0").get("countLowerBound");
        countBounds[0][1] = equipmentData.get("equipment0").get("countUpperBound");
        countBounds[1][0] = equipmentData.get("equipment1").get("countLowerBound");
        countBounds[1][1] = equipmentData.get("equipment1").get("countUpperBound");
        countBounds[2][0] = equipmentData.get("equipment2").get("countLowerBound");
        countBounds[2][1] = equipmentData.get("equipment2").get("countUpperBound");
        chargeBounds = new int[3][2];
        chargeBounds[0][0] = equipmentData.get("equipment0").get("chargeLowerBound");
        chargeBounds[0][1] = equipmentData.get("equipment0").get("chargeUpperBound");
        chargeBounds[1][0] = equipmentData.get("equipment1").get("chargeLowerBound");
        chargeBounds[1][1] = equipmentData.get("equipment1").get("chargeUpperBound");
        chargeBounds[2][0] = equipmentData.get("equipment2").get("chargeLowerBound");
        chargeBounds[2][1] = equipmentData.get("equipment2").get("chargeUpperBound");
        lifetimeBounds = new int[3][2];
        lifetimeBounds[0][0] = equipmentData.get("equipment0").get("usageTimeLowerBound");
        lifetimeBounds[0][1] = equipmentData.get("equipment0").get("usageTimeUpperBound");
        lifetimeBounds[1][0] = equipmentData.get("equipment1").get("usageTimeLowerBound");
        lifetimeBounds[1][1] = equipmentData.get("equipment1").get("usageTimeUpperBound");
        lifetimeBounds[2][0] = equipmentData.get("equipment2").get("usageTimeLowerBound");
        lifetimeBounds[2][1] = equipmentData.get("equipment2").get("usageTimeUpperBound");
        chargeTimeBounds = new int[3][2];
        chargeTimeBounds[0][0] = equipmentData.get("equipment0").get("chargingTimeLowerBound");
        chargeTimeBounds[0][1] = equipmentData.get("equipment0").get("chargingTimeUpperBound");
        chargeTimeBounds[1][0] = equipmentData.get("equipment1").get("chargingTimeLowerBound");
        chargeTimeBounds[1][1] = equipmentData.get("equipment1").get("chargingTimeUpperBound");
        chargeTimeBounds[2][0] = equipmentData.get("equipment2").get("chargingTimeLowerBound");
        chargeTimeBounds[2][1] = equipmentData.get("equipment2").get("chargingTimeUpperBound");

        HashMap<String, HashMap<String, Integer>> workingHourData = (HashMap<String, HashMap<String, Integer>>) data.get("workingHourConfig");
        shift1mins = (workingHourData.get("shift1").get("endHour") - workingHourData.get("shift1").get("startHour")) * 60;
        shift1mins += workingHourData.get("shift1").get("endMinute") - workingHourData.get("shift1").get("startMinute");
        shift2mins = (workingHourData.get("shift2").get("endHour") - workingHourData.get("shift2").get("startHour")) * 60;
        shift2mins += workingHourData.get("shift2").get("endMinute") - workingHourData.get("shift2").get("startMinute");
        HashMap<String, HashMap<String, Integer>> taskData = (HashMap<String, HashMap<String, Integer>>) data.get("taskConfig");
        taskCountBounds = new int[3][2];
        taskCountBounds[0][0] = taskData.get("task0").get("countLowerBound");
        taskCountBounds[0][1] = taskData.get("task0").get("countUpperBound");
        taskCountBounds[1][0] = taskData.get("task1").get("countLowerBound");
        taskCountBounds[1][1] = taskData.get("task1").get("countUpperBound");
        taskCountBounds[2][0] = taskData.get("task2").get("countLowerBound");
        taskCountBounds[2][1] = taskData.get("task2").get("countUpperBound");
        taskLengthBounds = new int[3][2];
        taskLengthBounds[0][0] = taskData.get("task0").get("lengthLowerBound");
        taskLengthBounds[0][1] = taskData.get("task0").get("lengthUpperBound");
        taskLengthBounds[1][0] = taskData.get("task1").get("lengthLowerBound");
        taskLengthBounds[1][1] = taskData.get("task1").get("lengthUpperBound");
        taskLengthBounds[2][0] = taskData.get("task2").get("lengthLowerBound");
        taskLengthBounds[2][1] = taskData.get("task2").get("lengthUpperBound");
        HashMap<String, Integer> stationData = (HashMap<String, Integer>) data.get("chargeStationConfig");
        stationBounds = new int[2];
        stationBounds[0] = stationData.get("countLowerBound");
        stationBounds[1] = stationData.get("countUpperBound");

        HashMap<String, Integer> employeeData = (HashMap<String, Integer>) data.get("employeeConfig");
        employeeBounds = new int[2];
        employeeBounds[0] = employeeData.get("countLowerBound");
        employeeBounds[1] = employeeData.get("countUpperBound");
    }


    public void produceData()
    {
        Random random=new Random();
        // random.setSeed(12);
        for (int i = 0; i < 3; i++) {
            counts[i] = random.nextInt(countBounds[i][1]-countBounds[i][0]) + countBounds[i][0];
            taskCounts[i] = random.nextInt(taskCountBounds[i][1]-taskCountBounds[i][0]) + taskCountBounds[i][0];
        }
        for (int i = 0; i < 3; i++) {
            initialCharges[i] = new ArrayList<Integer>();
            lifetimes[i] = new ArrayList<Integer>();
            chargeTimes[i] = new ArrayList<Integer>();
            taskLengths[i] = new ArrayList<Integer>();
            for (int j = 0; j < counts[i]; j++) {
                initialCharges[i].add(random.nextInt(chargeBounds[i][1]-chargeBounds[i][0])+chargeBounds[i][0]);
                lifetimes[i].add(random.nextInt(lifetimeBounds[i][1]-lifetimeBounds[i][0])+lifetimeBounds[i][0]);
                chargeTimes[i].add(random.nextInt(chargeTimeBounds[i][1]-chargeTimeBounds[i][0])+chargeTimeBounds[i][0]);

            }
            for (int j = 0; j <taskCounts[i] ; j++) {
                taskLengths[i].add(random.nextInt(taskLengthBounds[i][1]-taskLengthBounds[i][0])+taskLengthBounds[i][0]);
            }
        }
        workingHours = shift1mins+shift2mins;
        numStations = random.nextInt(stationBounds[1]-stationBounds[0]) + stationBounds[0];
        numWorkers = random.nextInt(employeeBounds[1]-employeeBounds[0]) + employeeBounds[0];
        names[0] = nameList.get(0);
        names[1] = nameList.get(1);
        names[2] = nameList.get(2);
    }

    public void output() throws IOException {
        FileWriter writer = new FileWriter("GeneratedData.txt");
        writer.write("Data for IE402 FORKLIFT_CHARGING Scheduling Project is given below!\n");
        writer.write("**GENERIC INFO**\n");
        writer.write("Workday length: "+workingHours+" mins\n");
        writer.write("Number of charging stations: "+numStations+"\n");
        writer.write("Number of workers available: "+numWorkers+"\n");
        writer.write("****\n**EQUIPMENT INFO**\n");
        for (int i = 0; i < 3; i++) {
            writer.write("\t----"+names[i]+"----\n");
            writer.write("\tNumber of available equipments: "+counts[i]+"\n");
            for (int j = 0; j < counts[i]; j++) {
                writer.write("\t\t- "+names[i]+" "+(j+1)+" : Charge = %"+initialCharges[i].get(j) +" , ChargeTime = "+chargeTimes[i].get(j)+" mins, LifeTime = "+lifetimes[i].get(j)+" mins\n");
            }
        }
        writer.write("****\n**TASK INFO**\n");
        for (int i = 0; i < 3; i++) {
            writer.write("\t----"+names[i]+"----\n");
            writer.write("\tNumber of tasks to be done: "+taskCounts[i]+"\n");
            for (int j = 0; j < taskCounts[i]; j++) {
                writer.write("\t\t- "+"Task "+" "+(j+1)+" : "+taskLengths[i].get(j) +" mins\n");
            }
        }
        writer.close();
    }
}
