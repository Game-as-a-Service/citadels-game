package tw.waterballsa.gaas.citadels.domain;


import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Selection {
    private final Map<Integer, String> pickets = new ConcurrentHashMap<>();
    private final List discardPile = Collections.EMPTY_LIST;

    public void select(String playerId, Integer roleCardId) {
        pickets.put(roleCardId, playerId);
    }

    public List<RoleCard> getNotSelected(List<RoleCard> roleCards) {
        List<RoleCard> notSelectRoleCards = new ArrayList<>();
        Set<Integer> collect = new HashSet<>(discardPile);
        roleCards.forEach(roleCard -> {
            if (!pickets.containsKey(roleCard.getSequence()) && !collect.contains(roleCard.getSequence())) {
                notSelectRoleCards.add(roleCard);
            }
        });
        return notSelectRoleCards;
    }
}
