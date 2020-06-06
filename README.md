# DungeonMaster
A small console game made in java.
Game Features
1. Console game built in Java and object oriented programming is implemented
2. Commerce State, Movement State and Combat Sate has been implemented and each state has specific available commands. 
3. Player has the ability to move towards different locations if there is an available exit. The exit can be locked and hence, to unlock it a key is needed which can be collected.
4. Locations can have multiple items or a non player character or a combination of both.
5. Player has the ability to collect items from the location. However, the player cannot exceed weight limit, which is setup by generating a random number from 1 to 46 and then mapping the random number to the weight limit table and retrieving the value. 
6. If the location has both non-player hostile character and items both, the player is only able to collect items once the hostile npc character is defeated. However, if the location has both non hostile player and collectable items, the player can talk with the player and collect simultaneously. 
7. Player can view and list items from the inventory.
8. Dedicated shop for buying and selling dedicated weapons, armors and shields. For instance, weapons can only be bought or sold in blacksmith shop, armors only in armor shop and shield only in shield shop. 
9.In order to buy something player has to have enough coin value and also should not exceed weight limit. Items are sold for half the value (price) and collectibles such as coin, potion and key cannot be sold or bought. Algorithm has been applied for the buying process.
10. Player can only equip one weapon, one armor and one shield at a time. Equipping any shield or armor will increase life points and vice versa because weapons and shield have bonus points.
11. Player has the ability to attack or flee in the presence of non hostile player. Attacking requires an equipped weapon from the inventory. On the other hand, if the player flees, it happens through a random available exit. If however the random exit is locked, player will loose a turn and will get attacked by hostile NPC. Similarly, equipping in combat state will also end the player’s turn.
12. Attacking mechanism for both player and non player character has been implemented. For player attacking damage is weapon damage + player strength and for non player character is highest number of 2 rolls of a 6 sided die + npc strength. Attacking damages for both sides are generated randomly through rolls of a die.
13. Player can restore life with 2 rolls of a 6 sided die (highest value is used), but restore during combat state will automatically end the turn for player.
14. Player can drop item from inventory and the item will be updated in the location. Player can unlock locations and can also talk with non hostile characters if present in the location.
15. Player can view maze status and personal status.

Comands
1. Collect
2. Move
3. Buy
4. Item
5. Equip
6. Unequip
7. Attack
8. Flee
9. Restore
10. Sell
11. Drop
12. Unlock
13. Talk
14. Status
15. Quit







