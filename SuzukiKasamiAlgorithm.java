import java.util.Queue;
import java.util.LinkedList;

class Process {
    private final int processId;
    private int sequenceNumber;
    private boolean hasToken;
    private Queue<Integer> requestQueue;

    public Process(int processId) {
        this.processId = processId;
        this.sequenceNumber = 0;
        this.hasToken = false;
        this.requestQueue = new LinkedList<>();
    }

    public void requestCriticalSection() {
        sequenceNumber++;
        requestQueue.add(sequenceNumber);
        System.out.println("Process " + processId + " requests critical section (seq: " + sequenceNumber + ")");
    }

    public void receiveToken() {
        hasToken = true;
        System.out.println("Process " + processId + " receives the token.");
        if (!requestQueue.isEmpty()) {
            int nextProcessId = requestQueue.poll();
            sendToken(nextProcessId);
        }
    }

    public void sendToken(int nextProcessId) {
        System.out.println("Process " + processId + " sends the token to Process " + nextProcessId);
        hasToken = false;
        // Simulate sending the token to the next process
    }
}

public class SuzukiKasamiAlgorithm {
    public static void main(String[] args) {
        int numProcesses = 5; // Number of processes in the ring

        // Create processes
        Process[] processes = new Process[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            processes[i] = new Process(i);
        }

        // Simulate process requests
        processes[2].requestCriticalSection(); // Example: Process 2 requests critical section
        // ... (other processes request as needed)

        // Simulate token circulation
        processes[0].receiveToken(); // Assume Process 0 starts with the token
    }
}
