import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String args[]){
       blockchain.add(new Block("hi there", "0"));
       blockchain.add(new Block("Hola", blockchain.get(blockchain.size()-1).hash));
       blockchain.add(new Block("Fuck you", blockchain.get(blockchain.size()-1).hash));

       String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
       System.out.println(blockChainJson);
    }

    public static boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;

        for(int i=1;i<blockchain.size();i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            // comparing hash of calculated and registered
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current hashes not equal");
                return false;
            }
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }

}
