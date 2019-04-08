package com.example.rapstargo;

import org.json.JSONObject;

import java.util.List;

public interface SocketEvent {
    public void onAPIDisconnection(String p_ErrorMsg);
    public void onUserDisconnection(String p_ErrorMsg);
    public void onAPIConnection();
    public void onUserConnectionSuccess();
    public void onUserConnectionFailed(String p_ErrorMsg);
    public void onUserReconnectionSuccess();
    public void onUserReconnectionFailed(String p_ErrorMsg);
    public void onAccountCreationSuccess();
    public void onAccountCreationFailed(String p_ErrorMsg);
    public void onLoggedAccountResultSucces(String p_ResultMessage);
    public void onLoggedAccountResultFailed(String p_ErrorMsg);
    public void onCharacterCreationSuccess();
    public void onCharacterCreationFailed(String p_ErrorMsg);
    public void onGetAllMyCharactersSuccess(List<Character> p_ListOfCharacters);
    public void onGetAllMyCharactersFailed(String p_ErrorMsg);
    public void onCharacterSelectionSuccess(Character p_CurrentCharacter);
    public void onCharacterSelectionFailed(String p_ErrorMsg);
    public void onGetCurrentCharacterSuccess(Character p_CurrentCharacter);
    public void onGetCurrentCharacterFailed(String p_ErrorMsg);
    public void onGetAllHubsSuccess(List<Hub> p_HubsList);
    public void onGetAllHubsFailed(String p_ErrorMsg);
    public void onConnectToHubSuccess(Hub p_Hub);
    public void onConnectToHubFailed(String p_ErrorMsg);
    public void onGetHubConnectedToSuccess(Hub p_Hub);
    public void onGetHubConnectedToFailed(String p_ErrorMsg);
    public void onExitHubSuccess();
    public void onExitHubFailed(String p_ErrorMsg);
    public void onCreateRoomSuccess();
    public void onCreateRoomFailed(String p_ErrorMsg);
    public void onAddRoomToHub(Room p_Room);
    public void onRemoveRoomToHub(String p_RoomId);
    public void onJoinRoomSuccess();
    public void onJoinRoomFailed(String p_ErrorMsg);
    public void onGetAllCharacterOfRoomSuccess(List<Character> p_CharacterList);
    public void onGetAllCharacterOfRoomFailed(String p_ErrorMsg);
    public void onExitCurrentRoomSuccess();
    public void onExitCurrentRoomFailed(String p_ErrorMsg);
    public void onLaunchFightSuccess();
    public void onLaunchFightFailed(String p_ErrorMsg);
    public void onFightIsLaunched();
    public void onBossAttack(List<Character> p_CharacterList);
    public void onFightIsFinished(boolean p_Victory);
    public void onUseCharacterAbilitySuccess();
    public void onUseCharacterAbilityFailed(String p_ErrorMsg);
    public void onBossTakeDamage();

}
