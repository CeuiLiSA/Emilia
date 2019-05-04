package ceuilisa.mirai.interf;

public interface MusicOperate {

    //void playMusic(long id, OnPrepare onPrepare);
    void playMusic(int index, OnPrepare onPrepare);

    void stopOrPlay();

    boolean isPlayingMusic();
}
