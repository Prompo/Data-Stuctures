public interface PriorityQueueInterface {
        void insert(City x);
        City max();
        City getmax();
        City remove(int id);
        boolean isEmpty();
        int size();
        void removeLast(City x);
    }

