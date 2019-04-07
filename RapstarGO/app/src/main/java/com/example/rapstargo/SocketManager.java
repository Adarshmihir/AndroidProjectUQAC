package com.example.tdufo.sockettry;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class SocketManager {
    private Socket mSocket;
    private String mSocketId;
    private String mPseudo;
    private Character mCharacter;
    private Hub mCurrentHub;
    private Room mCurrentRoom;

    public SocketEvent mCurrentActivity;

    /* Singleton pattern */
    private SocketManager() {
        try {
            IO.Options opts = new IO.Options();
            opts.reconnection = true;
            mSocket = IO.socket("http://158.69.197.146", opts);
        } catch (URISyntaxException e) {
        }
        mSocketId = "";
        mPseudo = "";

        mSocket.on(Socket.EVENT_DISCONNECT, handleDisconnection);
        mSocket.on(Socket.EVENT_CONNECT, handleConnection);
        mSocket.on("ping", sendPong);
        mSocket.on("reconnectionResult", handleIncomingReconnectionResult);
        mSocket.on("disconnectUser", handleUserDisconnection);
        mSocket.on("createAccountResult", handleIncomingCreateAccountResult);
        mSocket.on("loginResult", handleIncomingLoginResult);
        mSocket.on("loggedAccountResult", handleIncomingLoggedAccountResult);
        mSocket.on("createCharacterResult", handleIncomingCharacterCreationResult);
        mSocket.on("getAllMyCharactersResult", handleGetAllMyCharacterResult);
        mSocket.on("selectCharacterResult", handleSelectCharacterResult);
        mSocket.on("getCurrentCharacterResult", handleGetCurrentCharacterResult);
        mSocket.on("getAllHubsResult", handleGetAllHubsResult);
        mSocket.on("connectToHubResult", handleConnectToHubResult);
        mSocket.on("getHubConnectedToResult", handleGetHubConnectedToResult);
        mSocket.on("exitHubResult", handleExitHubResult);
        mSocket.on("createRoomResult", handleCreateRoomResult);
        mSocket.on("roomAddToHub", handleRoomAddToHub);
        mSocket.on("roomRemoveToHub", handleRoomRemoveToHub);
        mSocket.on("joinRoomResult", handleJoinRoomResult);
        mSocket.on("getAllUserOfRoom", handleGetAllUserOfRoom);
        mSocket.on("exitRoomResult", handleExitRoomResult);
        mSocket.on("launchFightResult", handleLaunchFightResult);
        mSocket.on("fightIsLaunched", handleFightIsLaunched);
        mSocket.on("applyDamageToRoomCharacters", handleApplyDamageToRoomCharacters);
        mSocket.on("fightIsFinished", handleFightIsFinished);
        mSocket.on("useCharacterAbilityResult", handleUseCharacterAbilityResult);
        mSocket.on("bossTakeDamage", handleBossTakeDamage);




        mSocket.connect();

    }

    private static SocketManager instance;

    public static SocketManager getInstance() {
        if (instance == null) {
            instance = new SocketManager();
        }
        return instance;
    }

    public Socket getmSocket() {
        return mSocket;
    }

    public String getmSocketId() {
        return mSocketId;
    }

    public void setmSocketId(String mSocketId) {
        this.mSocketId = mSocketId;
    }

    public String getmPseudo() {
        return mPseudo;
    }

    public void setmPseudo(String mPseudo) {
        this.mPseudo = mPseudo;
    }

    public Character getmCharacter() {
        return mCharacter;
    }

    public void setmCharacter(Character mCharacter) {
        this.mCharacter = mCharacter;
    }

    public Hub getmCurrentHub() {
        return mCurrentHub;
    }

    public void setmCurrentHub(Hub mCurrentHub) {
        this.mCurrentHub = mCurrentHub;
    }


    public Room getmCurrentRoom() {
        return mCurrentRoom;
    }

    public void setmCurrentRoom(Room mCurrentRoom) {
        this.mCurrentRoom = mCurrentRoom;
    }

    public boolean IsConnectedToUser()
    {
        if (mSocketId == null || mSocketId.length() == 0)
            return false;
        return true;
    }

    public void GetAllHubs()
    {
        JSONObject object = new JSONObject();
        mSocket.emit("getAllHubs", object);
    }

    public void Login(CharSequence p_pseudo, CharSequence p_password)
    {
        if(p_pseudo == null || p_pseudo.length() == 0)
        {
            try {
                mCurrentActivity.onUserConnectionFailed("Enter a pseudo.");
            } catch (Exception e)
            {
                return;
            }
        } else if(p_password == null || p_password.length() == 0)
        {
            try {
                mCurrentActivity.onUserConnectionFailed("Enter a password.");
            } catch (Exception e)
            {
                return;
            }
        } else {
            JSONObject object = new JSONObject();
            try {
                object.put("pseudo",p_pseudo);
                object.put("password",p_password);
                mSocket.emit("login", object);
            } catch (JSONException e) {
                try {
                    mCurrentActivity.onUserConnectionFailed("An error occured.");
                } catch (Exception err)
                {
                    return;
                }
            }
        }
    }

    public void CreateAccount(CharSequence p_pseudo, CharSequence p_password)
    {
        if(p_pseudo == null || p_pseudo.length() == 0)
        {
            try {
                mCurrentActivity.onAccountCreationFailed("Enter a pseudo.");
            } catch (Exception e)
            {
                return;
            }
        } else if(p_password == null || p_password.length() == 0)
        {
            try {
                mCurrentActivity.onAccountCreationFailed("Enter a password.");
            } catch (Exception e)
            {
                return;
            }
        } else {
            JSONObject object = new JSONObject();
            try {
                object.put("pseudo",p_pseudo);
                object.put("password",p_password);
                mSocket.emit("createAccount", object);
            } catch (JSONException e) {
                try {
                    mCurrentActivity.onAccountCreationFailed("An error occured.");
                } catch (Exception err)
                {
                    return;
                }
            }
        }
    }

    public void CreateCharacter(CharSequence p_name, CharSequence p_classId)
    {
        if(getmSocketId() == null || getmSocketId().length() == 0)
        {
            try {
                mCurrentActivity.onCharacterCreationFailed("Not connected.");
            } catch (Exception e)
            {
                return;
            }
        }
        else if(p_name == null || p_name.length() == 0)
        {
            try {
                mCurrentActivity.onCharacterCreationFailed("Enter a name.");
            } catch (Exception e)
            {
                return;
            }
        } else if(p_name == null || p_name.length() == 0)
        {
            try {
                mCurrentActivity.onCharacterCreationFailed("Choose a class.");
            } catch (Exception e)
            {
                return;
            }
        } else
        {
            JSONObject object = new JSONObject();
            try {
                object.put("name",p_name);
                object.put("classId",p_classId);
                mSocket.emit("createCharacter", object);
            } catch (JSONException e) {
                try {
                    mCurrentActivity.onCharacterCreationFailed("An error occured.");
                } catch (Exception err)
                {
                    return;
                }
            }
        }
    }

    public void GetLoggedAccount()
    {
        JSONObject object = new JSONObject();
        mSocket.emit("loggedAccount", object);
    }

    public void DisconnectUser()
    {
        JSONObject object = new JSONObject();
        mSocket.emit("disconnectUser", object);
    }

    public void GetAllMyCharacters()
    {
        JSONObject object = new JSONObject();
        mSocket.emit("getAllMyCharacters", object);
    }

    public void SelectCharacter(int _characterId)
    {
        JSONObject object = new JSONObject();
        try {
            object.put("idSelected", _characterId);
            mSocket.emit("selectCharacter", object);
        } catch (JSONException e)
        {
            return;
        }
    }

    public void GetCurrentCharacter()
    {
        Log.i("DIM", "GetCurrentCharacter: call");
        JSONObject object = new JSONObject();
        mSocket.emit("getCurrentCharacter", object);
    }

    public void ConnectToHub(int _id)
    {
        if(_id<0)
        {
            try {
                mCurrentActivity.onConnectToHubFailed("Hub id is negative.");
            } catch (Exception e)
            {
                return;
            }
        } else
        {
            JSONObject object = new JSONObject();
            try {
                object.put("hubId", _id);
                mSocket.emit("connectToHub", object);
            } catch (JSONException e)
            {
                return;
            }
        }
    }

    public void GetHubConnectedTo()
    {
        JSONObject object = new JSONObject();
        mSocket.emit("getHubConnectedTo", object);
    }

    public void ExitCurrentHub()
    {
        if(IsConnectedToUser())
        {
            JSONObject object = new JSONObject();
            mSocket.emit("exitHub", object);
        } else {
            try {
                mCurrentActivity.onExitHubFailed("Not connected.");
            } catch (Exception e)
            {
                return;
            }
        }
    }

    public void CreateRoom()
    {
        if(!IsConnectedToUser())
        {
            try {
                mCurrentActivity.onCreateRoomFailed("Not connected.");
            } catch (Exception e)
            {
                return;
            }
        } else {
            JSONObject object = new JSONObject();
            mSocket.emit("createRoom", object);
        }
    }

    public void JoinRoom(String _roomId)
    {
        if(!IsConnectedToUser())
        {
            try {
                mCurrentActivity.onJoinRoomFailed("Not connected.");
            } catch (Exception e)
            {
                return;
            }
        } else if(_roomId == null || _roomId.length() == 0)
        {
            try {
                mCurrentActivity.onJoinRoomFailed("Room id null.");
            } catch (Exception e)
            {
                return;
            }
        } else {
            JSONObject object = new JSONObject();
            try {
                object.put("roomId", _roomId);
                mSocket.emit("joinRoom", object);
            } catch (JSONException e)
            {
                return;
            }
        }
    }

    public void ExitCurrentRoom()
    {
        Log.i("DIM", "ExitCurrentRoom: call");
        JSONObject object = new JSONObject();
        mSocket.emit("exitRoom", object);
    }

    public void LaunchFight()
    {
        Log.i("DIM", "LaunchFight: call");
        JSONObject object = new JSONObject();
        mSocket.emit("launchFight", object);
    }

    public void UseAbility(String _AbilityId)
    {
        if(!IsConnectedToUser())
        {
            try {
                mCurrentActivity.onUseCharacterAbilityFailed("Not connected.");
            } catch (Exception e)
            {
                return;
            }
        } else if(_AbilityId == null || _AbilityId.length() == 0)
        {
            try {
                mCurrentActivity.onUseCharacterAbilityFailed("Ability id null.");
            } catch (Exception e)
            {
                return;
            }
        } else {
            JSONObject object = new JSONObject();
            try {
                object.put("abilityId", _AbilityId);
                mSocket.emit("useCharacterAbility", object);
            } catch (JSONException e)
            {
                return;
            }
        }
    }

    private Emitter.Listener handleDisconnection = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i("DIM", "Disconnection from internet.");
            try {
                mCurrentActivity.onAPIDisconnection("Disconnection from internet.");
            } catch (Exception e)
            {
                return;
            }
        }
    };

    private Emitter.Listener handleUserDisconnection = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            try {
                JSONObject body = data.getJSONObject("body");
                Log.i("DIM", "User disconnection.");
                try {
                    mCurrentActivity.onUserDisconnection(body.getString("message"));
                } catch (Exception e)
                {
                    return;
                }
            } catch (JSONException e)
            {
                return;
            }
        }
    };


    private Emitter.Listener sendPong = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            Log.i("DIM", "Ping received. ");
            JSONObject object = new JSONObject();
            mSocket.emit("pong", object);
        }
    };

    private Emitter.Listener handleConnection = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {


            Log.i("DIM", "Connection from socket.");
            Log.i("DIM", args.toString());

            try {
                mCurrentActivity.onAPIConnection();
            } catch (Exception e)
            {
                return;
            }


            JSONObject object = new JSONObject();
            try {
                object.put("socket_id", getmSocketId());
                object.put("pseudo", getmPseudo());

                mSocket.emit("reconnection", object);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };


    private Emitter.Listener handleIncomingReconnectionResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            String message;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    if(body.getBoolean("new_socket_id"))
                    {
                        setmSocketId(body.getString("socket_id"));
                    }
                    message = body.getString("message");
                    Log.i("DIM", message);
                    try {
                        mCurrentActivity.onUserReconnectionSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    message = body.getString("message");
                    Log.i("DIM", message);
                    try {
                        mCurrentActivity.onUserReconnectionFailed(message);
                    } catch (Exception e)
                    {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }

        }
    };


    private Emitter.Listener handleIncomingCreateAccountResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            String message;
            Log.i("DIM", "Account creation result");
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    SocketManager.getInstance().setmPseudo(body.getString("pseudo"));
                    SocketManager.getInstance().setmSocketId(body.getString("socket_id"));
                    try {
                        mCurrentActivity.onAccountCreationSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    message = body.getString("message");
                    try {
                        mCurrentActivity.onAccountCreationFailed(message);
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };


    private Emitter.Listener handleIncomingLoginResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            String message;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    SocketManager.getInstance().setmSocketId(body.getString("socket_id"));
                    SocketManager.getInstance().setmPseudo(body.getString("pseudo"));
                    try {
                        mCurrentActivity.onUserConnectionSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    message = body.getString("message");
                    Log.i("DIM", message);
                    try {
                        mCurrentActivity.onUserConnectionFailed(message);
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleIncomingLoggedAccountResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            String message;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    message = body.getString("message");
                    try {
                        mCurrentActivity.onLoggedAccountResultSucces(message);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    message = body.getString("message");
                    try {
                        mCurrentActivity.onLoggedAccountResultFailed(message);
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };


    private Emitter.Listener handleIncomingCharacterCreationResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    try {
                        mCurrentActivity.onCharacterCreationSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onCharacterCreationFailed(body.getString("message"));
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };


    private Emitter.Listener handleGetAllMyCharacterResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Log.i("DIM", "Get all character");
                    Log.i("DIM", body.toString());
                    Log.i("DIM", "/Get all character/");
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONArray("obj").toString();
                    Type listType = new TypeToken<List<Character>>(){}.getType();
                    List<Character> lCharacters = gson.fromJson(jsonOutput, listType);

                    for (Character _char:lCharacters) {
                        Log.i("DIM", _char.toString());
                    }
                    try {
                        mCurrentActivity.onGetAllMyCharactersSuccess(lCharacters);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onGetAllMyCharactersFailed(body.getString("message"));
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleSelectCharacterResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONObject("obj").toString();
                    Type listType = new TypeToken<Character>(){}.getType();
                    Character lCharacter = gson.fromJson(jsonOutput, listType);
                    if(lCharacter != null)
                    {
                        setmCharacter(lCharacter);
                    }
                    try {
                        mCurrentActivity.onCharacterSelectionSuccess(lCharacter);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onCharacterSelectionFailed(body.getString("message"));
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleGetCurrentCharacterResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            Log.i("DIM", data.toString());
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONObject("obj").toString();
                    Type listType = new TypeToken<Character>(){}.getType();
                    Character lCharacter = gson.fromJson(jsonOutput, listType);
                    Log.i("DIM", lCharacter.toString());
                    try {
                        mCurrentActivity.onGetCurrentCharacterSuccess(lCharacter);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onGetCurrentCharacterFailed(body.getString("message"));
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };



    private Emitter.Listener handleGetAllHubsResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONArray("obj").toString();
                    Type listType = new TypeToken<List<Hub>>(){}.getType();
                    List<Hub> lHubs = gson.fromJson(jsonOutput, listType);
                    Log.i("DIM", lHubs.toString());
                    try {
                        mCurrentActivity.onGetAllHubsSuccess(lHubs);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onGetAllHubsFailed(body.getString("message"));
                    } catch (Exception e)
                    {
                        return;
                    }
                }


            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleConnectToHubResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONObject("obj").toString();
                    Type listType = new TypeToken<Hub>(){}.getType();
                    Hub lHub = gson.fromJson(jsonOutput, listType);
                    Log.i("DIM", lHub.toString());
                    try {
                        setmCurrentHub(lHub);
                        mCurrentActivity.onConnectToHubSuccess(lHub);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onConnectToHubFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleGetHubConnectedToResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONObject("obj").toString();
                    Type listType = new TypeToken<Hub>(){}.getType();
                    Hub lHub = gson.fromJson(jsonOutput, listType);
                    Log.i("DIM", lHub.toString());
                    try {
                        mCurrentActivity.onGetHubConnectedToSuccess(lHub);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onGetHubConnectedToFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleExitHubResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    try {
                        mCurrentActivity.onExitHubSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onExitHubFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleCreateRoomResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    try {
                        mCurrentActivity.onCreateRoomSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onCreateRoomFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleRoomAddToHub = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            JSONObject body;
            try {
                body = data.getJSONObject("body");
                try {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONObject("obj").toString();
                    Type listType = new TypeToken<Room>(){}.getType();
                    Room lRoom = gson.fromJson(jsonOutput, listType);
                    Log.i("DIM", lRoom.toString());
                    mCurrentActivity.onAddRoomToHub(lRoom);
                } catch (Exception e)
                {
                    return;
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleRoomRemoveToHub = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            JSONObject body;
            try {
                body = data.getJSONObject("body");
                try {
                    mCurrentActivity.onRemoveRoomToHub(body.getString("room_id"));
                } catch (Exception e)
                {
                    return;
                }
            } catch (JSONException e) {
                return;
            }
        }
    };


    private Emitter.Listener handleJoinRoomResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONObject("obj").toString();
                    Type listType = new TypeToken<Room>(){}.getType();
                    Room lRoom = gson.fromJson(jsonOutput, listType);
                    if(lRoom != null)
                    {
                        setmCurrentRoom(lRoom);
                    }
                    try {
                        mCurrentActivity.onJoinRoomSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onJoinRoomFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleGetAllUserOfRoom = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    Gson gson = new Gson();
                    String jsonOutput = body.getJSONArray("obj").toString();
                    Type listType = new TypeToken<List<Character>>(){}.getType();
                    List<Character> lCharacters = gson.fromJson(jsonOutput, listType);

                    try {
                        getmCurrentRoom().setCharacter_list(lCharacters);
                        mCurrentActivity.onGetAllCharacterOfRoomSuccess(lCharacters);
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onGetAllCharacterOfRoomFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };


    private Emitter.Listener handleExitRoomResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    setmCurrentRoom(null);
                    try {
                        mCurrentActivity.onExitCurrentRoomSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onExitCurrentRoomFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };


    private Emitter.Listener handleLaunchFightResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    try {
                        mCurrentActivity.onLaunchFightSuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onLaunchFightFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleFightIsLaunched = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            JSONObject body;
            try {
                body = data.getJSONObject("body");
                try {
                    mCurrentActivity.onFightIsLaunched();
                } catch (Exception e)
                {
                    return;
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleApplyDamageToRoomCharacters = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            JSONObject body;
            try {
                body = data.getJSONObject("body");
                Gson gson = new Gson();
                String jsonOutput = body.getJSONArray("obj").toString();
                Type listType = new TypeToken<List<Character>>(){}.getType();
                List<Character> lCharacters = gson.fromJson(jsonOutput, listType);

                for (Character _char : lCharacters)
                {
                    if(mCharacter.getId() == _char.getId())
                    {
                        getmCharacter().setCurrent_life(_char.getCurrent_life());
                        getmCharacter().setAlive(_char.isAlive());
                        break;
                    }
                }
                try {
                    mCurrentActivity.onBossAttack(lCharacters);
                } catch (Exception e)
                {
                    return;
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleFightIsFinished = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            JSONObject body;
            try {
                body = data.getJSONObject("body");
                try {
                    boolean lVictory = body.getBoolean("victory");
                    if (lVictory)
                    {
                        getmCurrentRoom().getBoss().setLife(0);
                    }
                    mCurrentActivity.onFightIsFinished(lVictory);
                } catch (Exception e)
                {
                    return;
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleUseCharacterAbilityResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            boolean isSuccess;
            JSONObject body;
            try {
                isSuccess = data.getBoolean("success");
                body = data.getJSONObject("body");
                if(isSuccess)
                {
                    try {
                        mCurrentActivity.onUseCharacterAbilitySuccess();
                    } catch (Exception e)
                    {
                        return;
                    }
                } else {
                    try {
                        mCurrentActivity.onUseCharacterAbilityFailed(body.getString("message"));
                    } catch (Exception e) {
                        return;
                    }
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

    private Emitter.Listener handleBossTakeDamage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONObject data = (JSONObject) args[0];
            JSONObject body;
            try {
                body = data.getJSONObject("body");
                try {
                    int remainingLife = body.getInt("boss_life");
                    getmCurrentRoom().getBoss().setLife(remainingLife);
                    mCurrentActivity.onBossTakeDamage();
                    Log.i("FIGHT", "Remaining boss life : "+Integer.toString(remainingLife));
                } catch (Exception e)
                {
                    return;
                }
            } catch (JSONException e) {
                return;
            }
        }
    };

}
