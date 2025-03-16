//DS that stores multiple elemts in a contigous memory location
class DynamicArray{
    private int size;
    private int capacity;
    private int [] arr;

    public DynamicArray(int capacity){
        this.capacity = capacity;
        this.size = 0;
        arr = new int[this.capacity];
    }
    
    public int getIndex(int n){
        for(int i = 0;i < size;i++){
            if(arr[i]==n){
                return i;
            }
        }
        return -1;
    }
    
    public void add(int n){
        if(size == capacity){
            resize();
        }
        arr[size] = n;
        size++;
    }
    
    public void remove(int target){
        /*
        If there is no constraint of this array being ordered we 
        can minimize the time complexity of shifting by just  
        plugging in the last element in the removed location
        */
        int index = getIndex(target);
        
        if(index == -1) return;

        arr[index] = arr[size-1];
        size--;
        shrink();
    }
    
    public void insertAt(int index, int val){
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size >= capacity) {
            resize();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = val;
        size++;
    }
    
    public void resize(){
        this.capacity *=2;
        int [] newArr = new int[capacity];

        for(int i = 0;i < size;i++){
            newArr[i] = arr[i];
        }
        arr = null;//Helps the garbage cleaner free memory faster
        arr = newArr;
    }
    
    public void shrink() {
        if (size <= capacity / 4 && capacity > 10) {//Shrink when size is <= 25% of capacity
            capacity /= 2;
            int[] newArr = new int[capacity];

            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i]; 
            }

            arr = newArr;
        }
    }

    public int length(){
        return size;
    }

    public void set(int index,int newVal){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        arr[index] = newVal;
    }

    public int getMax(){
        if (size == 0) {
            throw new IllegalStateException("Array is empty");
        }
        int max = arr[0];
        for(int i = 1;i < size;i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
        
    }
    public int getMin(){
        if (size == 0) {
            throw new IllegalStateException("Array is empty");
        }
        int min = arr[0];
        for(int i = 1;i < size;i++){
            if(min > arr[i]){
                min = arr[i];
            }
        }
        return min;
    }

    public boolean contains(int n){
        return getIndex(n) != -1;
    }

    public void clear(){
        size = 0;
    }

    public boolean isEmpty(){
       return size == 0;
    }
}