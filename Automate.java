/**
 * Created by Marwen on 12/8/16.
 */
public abstract class Automate {

    int i;
    int etat,old;
    String s;

    public Automate(String s){
        this.s = s;
        i = 0;
        etat = 0;
        old = 0;
    }

    public void etape(){
        if(i < s.length() && etat != -1){
            suivant();
        }
    }

    public void toutesEtapes(){
        while (i < s.length() && etat != -1){
            System.out.println("Etape : " + etat + ", Reste : " + getReste());
            suivant();
        }
    }
    public boolean pasFin(){
        return i < s.length() && etat != -1 && s.length() != 0 && (s.charAt(i) == 'a' || s.charAt(i) == 'b');
    }
    public String getChaine(){
        return s;
    }
    public int getEtape(){
        return etat;
    }
    public String getReste(){
        return s.substring(i);
    }

    public abstract void suivant();
    public abstract byte[] generateAutomateImg();
    public abstract boolean accept();
}
