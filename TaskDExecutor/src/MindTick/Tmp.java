package MindTick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dileep.kumar on 8/3/17.
 */
public class Tmp {

    static Map<String, List<String>> taskDependency = new HashMap<>();

    static Map<String, List<String>> isDependencyOf = new HashMap(); // T1 - x, T2 - T1,T3 - T1,T4 - T3

    static volatile int numberOfexecutedTask = 0;

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("T2");
        list.add("T3");
        taskDependency.put("T1",list);

        List<String> list1 = new ArrayList<>();
        list1.add("T4");
        taskDependency.put("T3",list1);

        taskDependency.put("T2",null);
        taskDependency.put("T4",null);

        List<String> isDepndOf = new ArrayList<>();
        isDepndOf.add("T1");
        isDependencyOf.put("T2",isDepndOf);
        isDepndOf = new ArrayList<>();
        isDepndOf.add("T1");
        isDependencyOf.put("T3",isDepndOf);
        isDepndOf = new ArrayList<>();
        isDepndOf.add("T3");
        isDependencyOf.put("T4",isDepndOf);

        Tmp tmp = new Tmp();
        int totalTask = 4;
        while (numberOfexecutedTask < totalTask) {
            tmp.runTask();
        }
    }

    void runTask() {
        Task t = getTaskWhoseDependcyListEmpty();
        t.execute();

        numberOfexecutedTask++;

        markTAsExecuted(t.getTaskId());

    }

    Task getTaskWhoseDependcyListEmpty() {
        Task t = new Task();
        Iterator<String> itr = taskDependency.keySet().iterator();
        while(itr.hasNext())
        {
            String key = itr.next();
            if(taskDependency.get(key) ==null || taskDependency.get(key).size()==0)
            {
                t.setTaskId(key);
                break;
            }
        }
        return t;
    }

    void markTAsExecuted(String dependecyTask) {
        taskDependency.remove(dependecyTask);
        if(isDependencyOf.get(dependecyTask)!=null){
        for (String task : isDependencyOf.get(dependecyTask)) {
            removeTastFromTaskDependency(task, dependecyTask);
        }
        }
    }

    void removeTastFromTaskDependency(String task, String dependecyTask) {
        List taskList = taskDependency.get(task);
        taskList.remove(dependecyTask);

    }
}
