package ceuilisa.mirai.utils;

public class MergeLrc {

    private String chineseLrc;
    private String originalLrc;

    public MergeLrc(String chineseLrc, String originalLrc) {
        this.chineseLrc = chineseLrc;
        this.originalLrc = originalLrc;
    }

    public static String mergeLrc(String chineseLrc, String originalLrc) {
        StringBuffer stringBuffer = new StringBuffer();
        String[] separate = originalLrc.split("\n");
        String[] separate2 = chineseLrc.split("\n");

        for (int i = 0; i < separate.length; i++) {
            if (separate[i].contains(".") && separate[i].length() > 11) {
                stringBuffer.append(separate[i]);
                stringBuffer.append(" ");
                for (int j = 0; j < separate2.length; j++) {
                    if (separate2[j].contains(separate[i].substring(0, 10))) {
                        stringBuffer.append("                        ");
                        stringBuffer.append(separate2[j].substring(10));
                        stringBuffer.append("\n");
                        break;
                    }
                }

            }
        }
        Common.showLog(stringBuffer.toString());
        Common.showLog(originalLrc);
        return stringBuffer.toString();
    }
}
