

    interface ADT {
        void insert(int item);   
        int remove();          
        int peek();
        boolean isEmpty();
        int size();
    }

  
    class IntStack implements ADT {
        private int[] stack;
        private int top;

        public IntStack(int capacity) {
            stack = new int[capacity];
            top = -1;
        }

        public void insert(int item) {
            if (top < stack.length - 1) {
                stack[++top] = item;
            } else {
                System.out.println("Stack Overflow");
            }
        }

        public int remove() {
            if (!isEmpty()) {
                return stack[top--];
            }
            System.out.println("Stack Underflow");
            return -1;
        }

        public int peek() {
            if (!isEmpty()) {
                return stack[top];
            }
            return -1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public int size() {
            return top + 1;
        }
    }


    class IntQueue implements ADT {
        private int[] queue;
        private int front, rear, count;

        public IntQueue(int capacity) {
            queue = new int[capacity];
            front = 0;
            rear = -1;
            count = 0;
        }

        public void insert(int item) {
            if (count < queue.length) {
                rear = (rear + 1) % queue.length;
                queue[rear] = item;
                count++;
            } else {
                System.out.println("Queue Overflow");
            }
        }

        public int remove() {
            if (!isEmpty()) {
                int item = queue[front];
                front = (front + 1) % queue.length;
                count--;
                return item;
            }
            System.out.println("Queue Underflow");
            return -1;
        }

        public int peek() {
            if (!isEmpty()) {
                return queue[front];
            }
            return -1;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public int size() {
            return count;
        }
    }

    public class Main {

    
    public static void main(String[] args) {
        System.out.println("Stack Example:");
        ADT stack = new IntStack(5);
        stack.insert(10);
        stack.insert(20);
        System.out.println("Top of stack: " + stack.peek());
        System.out.println("Removed from stack: " + stack.remove());
        System.out.println("Stack size: " + stack.size());

        System.out.println("\nQueue Example:");
        ADT queue = new IntQueue(5);
        queue.insert(100);
        queue.insert(200);
        System.out.println("Front of queue: " + queue.peek());
        System.out.println("Removed from queue: " + queue.remove());
        System.out.println("Queue size: " + queue.size());
    }
}
