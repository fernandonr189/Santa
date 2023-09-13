import java.io.IOException;
import java.util.ArrayList;

public class Buffer {

    private ArrayList<Deer> deerList;
    private boolean deerAwaiting;
    private boolean deerListFull;
    private boolean deerListEmpty;
    private int deerInList;
    private int deerListSize;



    private ArrayList<Goblin> goblinsList;
    private boolean goblinListFull;
    private boolean goblinListEmpty;
    private boolean goblinsAwaiting;
    private int goblinsInList;
    private int goblinsListSize;

    public Buffer(int _deerListSize, int _goblinListSize) {
        this.deerList = new ArrayList<Deer>(_deerListSize);
        this.deerAwaiting = false;
        this.deerListFull = false;
        this.deerListEmpty = true;
        this.deerListSize = _deerListSize;
        this.deerInList = 0;

        this.goblinsList = new ArrayList<Goblin>(_goblinListSize);
        this.goblinsAwaiting = false;
        this.goblinListFull = false;
        this.goblinListEmpty = true;
        this.goblinsListSize = _goblinListSize;
        this.goblinsInList = 0;
    }

    public synchronized void produceGoblin(Goblin _newGoblin) {
        while(goblinListFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        goblinsList.add(_newGoblin);
        goblinsInList++;
        printStatus();
        goblinsAwaiting = goblinsInList > 3;
        if(goblinsInList == goblinsListSize) {
            goblinListFull = true;
        }
        notifyAll();
    }

    public synchronized void produceDeer(Deer _newDeer) {
        while(deerListFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        deerList.add(_newDeer);
        deerInList++;
        printStatus();
        deerAwaiting = deerInList > 9;
        if(deerInList == deerListSize) {
            deerListFull = true;
        }
        notifyAll();
    }

    public void printStatus() {
        System.out.println("Duendes: " + goblinsInList);
        System.out.println("Renos: " + deerInList);
        System.out.println();
    }
}
