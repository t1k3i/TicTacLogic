public class Triple<T, U, V> {
    
    private T first;
    private U second;
    private V third;

    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T fst() {
        return this.first;
    }

    public U snd() {
        return this.second;
    }

    public V thd() {
        return this.third;
    }

}
