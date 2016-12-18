import com.github.jabbalaci.graphviz.GraphViz;

/**
 * Created by Marwen on 12/8/16.
 * automate (aa)*(ab+a)
 */
public class Automate2 extends Automate{


    public Automate2(String s){
        super(s);
    }
    public boolean accept(){
        return etat == 2 || etat == 1;
    }

    public void suivant(){
        old = etat;
        switch (etat) {
            case 0:
                if (s.charAt(i) == 'a')
                    etat = 1;
                else
                    etat = -1;
                break;
            default:
                if (s.charAt(i) == 'b')
                    etat = 2;
                else
                    if (s.charAt(i) == 'a')
                        etat = 0;
                    else
                        etat = -1;
        }
        i++;
    }

    public byte[] generateAutomateImg(){
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        gv.addln("rankdir=LR;");
        gv.addln("size=\"8,5\"");
        gv.addln("q1 [shape = doublecircle];");
        gv.addln("q2 [shape = doublecircle];");
        gv.addln("node [shape = circle];");
        gv.addln("node[color=black];");
        gv.addln("q"+ ((etat == -1) ? 3 : etat) +"[color=red];");
        gv.addln("q0 -> q1 [ label = \"a\"" + ( (old == 0 && etat== 1) ? ",color=\"0.002 0.999 0.999\"" : "" ) +  "] ;");
        gv.addln("q1 -> q2 [ label = \"b\"" + ( (old == 1 && etat== 2) ? ",color=\"0.002 0.999 0.999\"" : "" ) +  "] ;");
        gv.addln("q1 -> q0 [ label = \"a\"" + ( (old == 1 && etat== 0) ? ",color=\"0.002 0.999 0.999\"" : "" ) +  "] ;");
        gv.addln("q2 -> q0 [ label = \"a\"" + ( (old == 2 && etat== 1) ? ",color=\"0.002 0.999 0.999\"" : "" ) +  "] ;");

        gv.addln("q0 -> q3 [ label = \"b\"" + ( (old == 0 && etat== -1) ? ",color=\"0.002 0.999 0.999\"" : "" ) +  "] ;");
        gv.addln("q2 -> q3 [ label = \"b\"" + ( (old == 2 && etat== -1) ? ",color=\"0.002 0.999 0.999\"" : "" ) +  "] ;");
        gv.addln(gv.end_graph());
        String repesentationType= "dot";
        String type = "png";
        return  gv.getGraph(gv.getDotSource(), type, repesentationType);
    }

}
