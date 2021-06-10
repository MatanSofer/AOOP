package Virus;

import java.util.Random;


public class VirusManager {

    public enum Variants {
        ChineseVariant(new ChineseVariant(),"Chinese Variant"),
        BritishVariant(new BritishVariant(),"British Variant"),
        SouthAfricanVariant(new SouthAfricanVariant(), "SouthAfricanVariant");
    	
        private final String type;
        private final IVirus virus;

        Variants(IVirus virus,String type){
            this.virus=virus;
            this.type=type;

        }
        public Variants[] getVariants(){
            return Variants.values();
        }
        public static int findVirus(IVirus v) {
            for (int i = 0; i < Variants.values().length; i++) {
                if (Variants.values()[i].virus.isEqual(v) )
                    return i;

            }
            return -1;

        }
        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return this.type;
        }
        public IVirus getVirus()
        {
            return this.virus;
        }


    }

    private static Boolean [][] data;

    static {
        data=new Boolean[Variants.values().length][];
        for(int i=0;i<data.length;i++)
        {
            data[i]=new Boolean[Variants.values().length];
            for(int j=0;j<data.length;j++)
            {
                if(i!=j)
                    data[i][j]=false;
                else
                    data[i][j]=true;
            }
        }
    }
    
    
    public static void Change(int i, int j)
    {
        data[i][j]=!data[i][j];
    }

    public static IVirus randomContagion(IVirus virus)
    {
        int index = Variants.findVirus(virus);
        if(index==-1) {
            return null;
        }
        IVirus v=findMutation(data[index]);
        return v;
    }
    
    public static IVirus findMutation(Boolean[] data)
    {
        int size=0;
        int[] indexes=null;
        for(int i=0;i<data.length;i++)
        {
            if(data[i])
            {
                size++;
            }
        }
        indexes=new int[size];
        int j=0;
        for(int i=0;i<size;i++)
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
        return data;
    }

}

