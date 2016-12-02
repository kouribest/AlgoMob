package Project;

/**
 * Created by skouriba on 30/11/16.
 */
import jbotsim.Node;
        import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Drone extends VectorNode {
    Cherry nextCherry;

    @Override
    public void onStart() {
        super.onStart();
        setSensingRange(20);
        setIcon("/Project/drone.png"); // To be adapted (package path)
        setSize(14);
        onPointReached(getLocation());
    }

    @Override
    public void onPointReached(Point2D point) {
        if (nextCherry == null) {
            //nextCherry = (Cherry) getTopology().getNodes().get(0);
            nextCherry = (Cherry) getNext();
        }
        travelTo(nextCherry.getLocation());
    }

    @Override
    public void onSensingIn(Node node) {
        if (node instanceof Cherry) {
            getTopology().removeNode(node);
            nextCherry = null;
        }
    }

    public Node getNext (){
        List<Node> listNode = new ArrayList<>();
        listNode = this.getTopology().getNodes();
        listNode.remove(listNode.size()-1);
        Node nearest = null;
        double min = Double.MAX_VALUE;
        for (Node node : listNode){
            if (distance(node) < min){
                min = distance(node);
                nearest = node;
            }
        }
        System.out.println("toto");
        return nearest;
    }
}