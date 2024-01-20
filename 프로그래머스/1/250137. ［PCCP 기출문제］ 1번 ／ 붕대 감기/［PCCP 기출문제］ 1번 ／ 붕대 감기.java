import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int attackMaxTime = Arrays.stream(attacks)
            .mapToInt(attack -> attack[0])
            .max()
            .orElse(0);
        
        int[] attackInfo = new int[attackMaxTime + 1];
        for (int[] attack : attacks) {
            attackInfo[attack[0]] = attack[1];
        }
        
        int currentHealth = health;
        int successCount = 0;
        
        for (int i = 0; i < attackInfo.length; i++) {
            int currentAttack = attackInfo[i];
            
            if (currentAttack == 0) {
                successCount++;
                
                int recoveryAmount = bandage[1];
                if (successCount == bandage[0]) {
                    recoveryAmount += bandage[2];
                    successCount = 0;
                }
                
                currentHealth = Math.min(health, currentHealth + recoveryAmount);
                
                continue;
            }
            
            successCount = 0;
            
            currentHealth -= currentAttack;
            if (currentHealth <= 0) {
                return -1;
            }
        }
        
        return currentHealth;
    }
}