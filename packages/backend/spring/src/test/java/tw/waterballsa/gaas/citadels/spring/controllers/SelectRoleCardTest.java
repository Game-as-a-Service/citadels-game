package tw.waterballsa.gaas.citadels.spring.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import tw.waterballsa.gaas.citadels.domain.Player;
import tw.waterballsa.gaas.citadels.spring.CitadelsSpringBootTest;
import tw.waterballsa.gaas.citadels.spring.controllers.viewmodel.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tw.waterballsa.gaas.citadels.domain.CitadelsGame.DEFAULT_CARD_QUANTITY;
import static tw.waterballsa.gaas.citadels.domain.CitadelsGame.DEFAULT_COINS;

public class SelectRoleCardTest extends CitadelsSpringBootTest {

    public static GameController.UserRequest p1 = new GameController.UserRequest("1", "p1", "1");
    public static GameController.UserRequest p2 = new GameController.UserRequest("2", "p2", "2");
    public static GameController.UserRequest p3 = new GameController.UserRequest("3", "p3", "3");
    public static GameController.UserRequest p4 = new GameController.UserRequest("4", "p4", "4");
    public static GameController.UserRequest p5 = new GameController.UserRequest("5", "p5", "5");

    @Test
    public void whenRoomReady_ShouldGetStartGameSuccessfully() {
        GameController.StartGameRequest request = getTestGame();
        StartGameView startGameView = createGame(request);

        GetGameView getGameViewResponse = getGame(startGameView.getGameId());
        GameView gameView = getGameViewResponse.getGameView();
        PlayerView playerView = gameView.getPlayerViews().get(1);

        // api get game roles cards list
        List<RoleCardView> roleCardViews = gameView.getRoleCardViews();

        // select role card king from roleCardViews
        Optional<RoleCardView> optionalRoleCardView = select("國王", roleCardViews);

        // get all role cards api
        // 關聯類別的紀錄玩家取得角色卡 紀錄玩家 還有棄牌堆

        // select role card king from roleCardViews failed

        RoleCardView roleCardView = optionalRoleCardView.get();
        selectOneRoleCard(playerView, roleCardView);
    }

    @Test
    public void whenRoomReady_ShouldGetAllRoleCardsSuccessfully() throws Exception {
        GameController.StartGameRequest request = getTestGame();
        StartGameView startGameView = createGame(request);

        GetGameView getGameViewResponse = getGame(startGameView.getGameId());
        GameView gameView = getGameViewResponse.getGameView();
        List<RoleCardView> roleCardViews = gameView.getRoleCardViews();

        // GetSelectedRoleCards
        MvcResult mvcResult = mockMvc.perform(get(API_PREFIX + "/games/" + gameView.getId() + "/rolecards")
                                        .contentType(APPLICATION_JSON))
                                        .andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void givenCharacterAChoosesCardWithCrown_whenSelectionIsMade_thenSelectionShouldBeSuccessful() {
        GameController.StartGameRequest request = getTestGame();
        StartGameView startGameView = createGame(request);
        GetGameView getGameViewResponse = getGame(startGameView.getGameId());
        PlayerView playerA = getPlayerHasCrown(getGameViewResponse);
        Optional<RoleCardView> select = select("騎士", getGameViewResponse.getGameView().getRoleCardViews());

        selectOneRoleCard(playerA, select.get());

        GetGameView game = getGame(startGameView.getGameId());
        PlayerView playerP1 = getPlayer(game.getGameView().getPlayerViews(), p1.getName());
        RoleCardView roleCardView = playerP1.getRoleCardView();
        assertNotNull(roleCardView);
        assertEquals(1, roleCardView.getSequence());
    }


    //只能從現有的角色選擇一個
//    Given : 現有角色 1、2、3、4，玩家 A、B、C、D (玩家順序 A -> B -> C -> D)
//    When :玩家Ａ選擇角色 1
//    Then: 將角色2、3、4 交給玩家 B 選擇
    @Test
    public void givenPlayerASelectsRole1_whenSelectionIsMade_thenPlayerBShouldSelectFromRole2ToRole4() {
        GameController.StartGameRequest request = getTestGame();
        StartGameView startGameView = createGame(request);
        GetGameView getGameViewResponse = getGame(startGameView.getGameId());
        PlayerView playerA = getPlayerHasCrown(getGameViewResponse);
        Optional<RoleCardView> select = select("騎士", getGameViewResponse.getGameView().getRoleCardViews());

        selectOneRoleCard(playerA, select.get());

        GetGameView game = getGame(startGameView.getGameId());
        PlayerView playerP1 = getPlayer(game.getGameView().getPlayerViews(), p1.getName());
        RoleCardView roleCardView = playerP1.getRoleCardView();
        assertNotNull(roleCardView);
        assertEquals(1, roleCardView.getSequence());

        PlayerView playerP2 = getPlayer(game.getGameView().getPlayerViews(), p2.getName());
        RoleCardView roleCardViewP2 = playerP2.getRoleCardView();
        assertNotNull(roleCardViewP2);
        assertEquals(2, roleCardViewP2.getSequence());
    }


    //    有皇冠的角色要先選擇role card
//    Given : 現有角色 1、2、3、4,玩家 A、B、C 、D (玩家順序 A -> B -> C -> D)
//    When : 玩家 A 擁有皇冠
//    Then: 玩家 A 從角色 1、2、3、4 選擇角色
    @Test
    public void givenPlayerAHasCrown_whenSelectionIsMade_thenPlayerAShouldSelectFromRole1ToRole4() {
        GameController.StartGameRequest request = getTestGame();
        StartGameView startGameView = createGame(request);
        GetGameView getGameViewResponse = getGame(startGameView.getGameId());
        PlayerView playerA = getPlayerHasCrown(getGameViewResponse);
        Optional<RoleCardView> select = select("騎士", getGameViewResponse.getGameView().getRoleCardViews());

        selectOneRoleCard(playerA, select.get());

        GetGameView game = getGame(startGameView.getGameId());
        PlayerView playerP1 = getPlayer(game.getGameView().getPlayerViews(), p1.getName());
        RoleCardView roleCardView = playerP1.getRoleCardView();
        assertNotNull(roleCardView);
        assertEquals(1, roleCardView.getSequence());
    }


    private PlayerView getPlayer(List<PlayerView> playerViews, String playerName) {
        return playerViews.stream().filter(playerView -> playerName.equalsIgnoreCase(playerView.getName())).findAny().get();
    }

    private PlayerView getPlayerHasCrown(GetGameView getGameViewResponse) {
        return getGameViewResponse.getGameView().getPlayerViews().stream().filter(PlayerView::getHasCrown).findAny().get();
    }


    private void selectOneRoleCard(PlayerView playerView, RoleCardView roleCardView) {
        // put update game card and player handCards
    }

    private Optional<RoleCardView> select(String cardName, List<RoleCardView> roleCardViews) {
        return roleCardViews.stream().filter(roleCardView -> cardName.equalsIgnoreCase(roleCardView.getName())).findAny();
    }


    @SneakyThrows
    private GetGameView getGame(String gameId) {
        ResultActions mvcResult = mockMvc.perform(get(API_PREFIX + "/games/{gameId}", gameId))
                .andExpect(status().isOk());
        return getBody(mvcResult, GetGameView.class);
    }


    private GameController.StartGameRequest getTestGame() {
        List<GameController.UserRequest> players = Arrays.asList(p1, p2, p3, p4, p5);
        return new GameController.StartGameRequest("1", "roomA", "1", players);
    }

    @SneakyThrows
    private StartGameView createGame(GameController.StartGameRequest startGameRequest) {
        ResultActions resultActions = mockMvc.perform(post(API_PREFIX + "/games")
                        .contentType(APPLICATION_JSON)
                        .content(toJson(startGameRequest)))
                .andExpect(status().isOk());
        return getBody(resultActions, StartGameView.class);
    }
}
