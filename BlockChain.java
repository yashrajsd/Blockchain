import java.security.Security;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import com.google.gson.GsonBuilder;

public class BlockChain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>();
    public static int difficulty = 6;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String args[]){
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
        walletA = new Wallet();
        walletB = new Wallet();

        System.out.println("Private and Public Keys: ");
        System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
        System.out.println(StringUtil.getStringFromKey(walletA.publicKey));

        Transaction transaction = new Transaction(walletA, walletB, difficulty,null);
        transaction.generateSignature(walletA.privateKey);

        System.out.println("Is signature verified");
        System.out.println(transaction.verifySignature());
        

        // blockchain.add(new Block("hi there", "0"));
        // System.out.println("Trying to )mine block 1...");
        // blockchain.get(0).mineBlock(difficulty);

        // blockchain.add(new Block("Hola", blockchain.get(blockchain.size()-1).hash));
        // blockchain.get(1).mineBlock(difficulty);

        // blockchain.add(new Block("Fuck you", blockchain.get(blockchain.size()-1).hash));
        // blockchain.get(2).mineBlock(difficulty);

        // System.out.println("Blockchain is valid: "+isChainValid());

        // String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        // System.out.println(blockChainJson);
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
