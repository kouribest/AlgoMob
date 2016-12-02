package Project;

/**
 * Created by skouriba on 30/11/16.
 */
import jbotsim.Node;
import jbotsim.Topology;
import jbotsim.event.ClockListener;
import jbotsim.ui.JViewer;
import jbotsim.ui.painting.BackgroundPainter;

import java.awt.Graphics2D;

public class Competition implements ClockListener, BackgroundPainter{
    Topology tp;
    Integer score;

    public Competition() {
        tp = new Topology(1000, 800);
        tp.pause();
        //distributeNodes(tp);
        //Project.DistributeNodes.distributeNodes1(tp);
        //Project.DistributeNodes.distributeNodes2(tp);
        Project.DistributeNodes.distributeNodes3(tp);
        tp.addClockListener(this);
        tp.setClockSpeed(10000);
        JViewer jv = new JViewer(tp);
        jv.getJTopology().addBackgroundPainter(this);
    }

    public void distributeNodes(Topology tp){
        for (int i=0; i<20; i++) {
            double x = Math.random() * 800 + 100;
            double y = Math.random() * 600 + 100;
            tp.addNode(x, y, new Cherry());
        }
        tp.addNode(500, 400, new Drone());
    }

    @Override
    public void onClock() {
        if (tp.getNodes().size() == 1) {
            score = (int) Math.ceil(tp.getTime()/10.0);
            tp.pause();
        }
    }

    @Override
    public void paintBackground(Graphics2D g2d, Topology tp) {
        if (score != null) {
            Node drone = tp.getNodes().get(0);
            String s = "Score: "+Integer.toString(score);
            g2d.drawString(s, (int) drone.getX()+30, (int) drone.getY()+30);
        }
    }

    public static void main(String[] args) {
        new Competition();
    }
}