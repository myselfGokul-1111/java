


    interface SetADT {
        boolean add(int item);
        boolean remove(int item);
        boolean contains(int item);
        SetADT union(SetADT otherSet);
    }


   class IntSet implements SetADT {
        private int[] elements;
        private int size;

        public IntSet() {
            elements = new int[100];
            size = 0;
        }

 
        public boolean add(int item) {
            if (contains(item)) return false; 
            elements[size++] = item;
            return true;
        }

    
        public boolean remove(int item) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == item) {
          
                    for (int j = i; j < size - 1; j++) {
                        elements[j] = elements[j + 1];
                    }
                    size--;
                    return true;
                }
            }
            return false;
        }

        public boolean contains(int item) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == item) return true;
            }
            return false;
        }

    
        public SetADT union(SetADT otherSet) {
            IntSet result = new IntSet();
          
            for (int i = 0; i < this.size; i++) {
                result.add(this.elements[i]);
            }
  
            if (otherSet instanceof IntSet) {
                IntSet other = (IntSet) otherSet;
                for (int i = 0; i < other.size; i++) {
                    result.add(other.elements[i]);
                }
            }
            return result;
        }

       
        public void display() {
            System.out.print("Set: ");
            for (int i = 0; i < size; i++) {
                System.out.print(elements[i] + " ");
            }
            System.out.println();
        }
    }
    public class Main2 {

    public static void main(String[] args) {
        IntSet set1 = new IntSet();
        set1.add(5);
        set1.add(3);
        set1.add(9);
        set1.add(5); 
        System.out.println("Set 1:");
        set1.display();

        IntSet set2 = new IntSet();
        set2.add(3);
        set2.add(7);
        set2.add(1);
        System.out.println("Set 2:");
        set2.display();

        SetADT unionSet = set1.union(set2);
        System.out.println("Union of Set 1 and Set 2:");
        ((IntSet) unionSet).display();
    }
}
