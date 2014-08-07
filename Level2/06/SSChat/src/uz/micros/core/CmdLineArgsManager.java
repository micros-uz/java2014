package uz.micros.core;

public class CmdLineArgsManager {
    private String[] args;

    public void init(String[] args) {
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }
}
