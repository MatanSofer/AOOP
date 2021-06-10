package Virus;

import java.util.Random;

public class VirusManager {
    public enum Variants { //varients enum hold all available viruses and their type
        ChineseVariant(new ChineseVariant(),"Chinese Variant"),
        BritishVariant(new BritishVariant(),"British Variant"),
        SouthAfricanVariant(new SouthAfricanVariant(), "SouthAfricanVariant");
    	
        private final String type;
        private final IVirus virus;

        Variants(IVirus virus,String type){
            this.virus=virus;
            this.type=type;

        }
        public Variants[] getVariants(){ //returns all values in varients enum
            return Variants.values();
        }
        public static int findVirus(IVirus v) { //get a virus and find if exist in all exist varients value , then returns the index
            for (int i = 0; i < Variants.values().length; i++) {
                if (Variants.values()[i].virus.isEqual(v) )
                    return i;
            }
            return -1;
        }
        public String toString() {//return type
            return this.type;
        }
        public IVirus getVirus() { //return virus 
            return this.virus;
        }
    }
    
    private static Boolean [][] data; //holds the 2D table array (the mutation window reciving values from this field )

    static { // a static block that is executed when defining the class 
        data=new Boolean[Variants.values().length][];
        for(int i=0;i<data.length;i++) {
            data[i]=new Boolean[Variants.values().length];
            for(int j=0;j<data.length;j++) {
                if(i!=j)
                    data[i][j]=false;
                else
                    data[i][j]=true;
            }
        }
    }
    public static void Change(int i, int j) { //Changes a value on the mutation data matrix from true to false and contraversa
        data[i][j] = !data[i][j];
    }
    public static IVirus randomContagion(IVirus virus) { //Finds a random mutation using the given virus by looking at data matrix
        int index = Variants.findVirus(virus);
        if(index==-1) {
            return null;
        }
        IVirus v=findMutation(data[index]);
        return (v == null)? virus : v; //prevents giving null value when there is no mutations enabled (returns the original virus)
    }
    public static IVirus findMutation(Boolean[] data) { //Finds a random mutation using the given boolean array
        int size=0;
        int[] indexes=null;
        for(int i=0;i<data.length;i++)
        {
            if(data[i])
                size++;
        }
        indexes=new int[size];
        int j=0;
        for(int i=0;i<data.length;i++)
        {
            if(data[i])
            {
                indexes[j]=i;
                j++;
            }
        }
        if(size==0)
            return null;
        Random r=new Random();
        int x=r.nextInt(size);
        return Variants.values()[indexes[x]].getVirus();

    }
    public static Boolean[][] getData() { 
        // returns a matrix with boolean values of the mutations as shown in mutation dialog
        return data;
    }

}

