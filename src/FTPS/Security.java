package FTPS;

/**
 * Created by George Walter Colrove IV on 3/22/2016.
 */
public class Security {
    public static String hashString(String input, long seed){
        String hashed ="";
        long finalHash = 0;
        long num = 1;
        if(seed != 0)
            num = seed;
        for(char a: input.toCharArray()) {
            long secondHash = 0;
            for (char b : input.toCharArray()) {
                long firstHash =0;
                for (char c : input.toCharArray()) {
                    firstHash = ((int)c * seed++)>>3;
                }
                secondHash += (((int)b) * firstHash) / seed++;
            }
            finalHash = ((int)(a * seed)/secondHash + secondHash) * seed++;
            String littleBit = finalHash + "";
            /*System.out.println(littleBit + " " + a);
            for(int i = 0; i < littleBit.length() - 1; i+=2){
                String parse = littleBit.substring(i, i + 2);
                hashed += (char)Integer.parseInt(parse);
            }*/
            hashed += littleBit;
        }
        return hashed;
    }
}
