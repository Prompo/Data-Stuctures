public class PQ implements PriorityQueueInterface {
        private City [] heap;
        private int size;
        private IntegerComparator comparator;
        private static final int default_capacity=10;
        private static final int autogrow_size=10;


        public PQ(IntegerComparator comparator){
            this.heap= new City[default_capacity+1];
            this.size=0;
            this.comparator= comparator;
        }


        @Override
        public boolean isEmpty(){
            return size==0;
        }

        @Override
        public int size(){
            return size;
        }

        @Override
        public  void insert(City x){
            if (size==((int)(size*0.75)))
                resize();
            heap[++size]=x;
            swim(size);
        }
        @Override
        public  City max(){
            if (size==0)
                return null;
            return heap[1];

        }

        @Override
        public City getmax() {
            if (size==0)
                return null;
            City root = heap[1];
            heap[1]=heap[size];
            size--;
            sink(1);
            return root;
        }

        private void swim(int i){
            if (i==1)
                return;
            int parent=i/2;
            while (i!=1 && comparator.compare(heap[i],heap[parent])>0) {
                swap(i,parent);
                i=parent;
                parent=i/2;
            }
            if (parent!=0 && comparator.compare(heap[i],heap[parent])>0){
                swap(i,parent);
                swim(parent);
            }
        }



        private void sink(int i){
            int left=2*i;
            int right=left+1;
            if (left>size)
                return;
            while (left <= size) {
                int max = left;
                if (right <= size) {
                    if (comparator.compare(heap[left], heap[right]) < 0)
                        max=right;
                }
                if (comparator.compare(heap[i],heap[max])>0)
                    return;
                else{
                    swap(i,max);
                    i=max;
                    left=i*2;
                    right=left+1;
                }

            }
            if (comparator.compare(heap[2],heap[1])>0)
                swap(1,2);

        }
        @Override
        public void removeLast(City x) {
            insert(x);
           if ( size>=3 &&comparator.compare(heap[3],heap[2])>0)
                swap(2,3);
            heap[size]=null;
            size--;
        }


        private void swap(int i, int j){
            City temp=heap[i];
            heap[i]=heap[j];
            heap[j]=temp;
        }

        private void resize(){
            City [] newHeap= new City[heap.length+autogrow_size];
            for (int i=0; i<=size; i++) {
                newHeap[i] = heap[i];
            }
            heap=newHeap;

        }

        @Override
        public City remove(int id){
            heap[size+1]=new City(id,"fake",1,1);
            City root = null;
            for (int i=1; i<=size; i++){
                if ((help(heap[i],heap[size+1]))==0) {
                    swap(1, i);
                    root=max();
                    getmax();
                    break;
                }
            }

            return root;
        }

        int  help(City t1,City t2){
            if (t1.ID==t2.ID)
                return 0;
            else
                return 1;
        }
}



