public class LinearQueue {
    int front=-1;
    int rear=-1;
    int size;
    int q[];

    LinearQueue(int size){
        this.size=size;
        q=new int[size];
    }
    boolean isFull(){
        return  rear==q.length-1;

    }

    boolean isEmpty(){
        return  front==-1 && rear==-1;
    }

    void enqueue(int data){
        if(isFull()){
            System.out.println("Over flow");
        }
        else{
            if(front==-1){
                front=0;

            }
            q[++rear]=data;
        }
    }
    int dequeue(){
        if(!isEmpty()){
            if(front==rear){
                int val=front;
                front=rear=-1;
                return q[val];

            }
            else{
                return q[front++];

            }
        }
        else{
            System.out.println("under flow");
            return -99999;
        }
    }
}
