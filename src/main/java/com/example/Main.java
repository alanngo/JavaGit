package com.example;

import static com.example.logger.MyLogger.*;
import static com.example.util.GitCommands.*;

public class Main
{

    public static void main(String[] args)
    {
        String uri ="https://github.com/alanngo/yeetcode.git";
        String commitSHA = "3dfc94dd89b1bd2d30f738d63fd213820ea1a496";
        String pathName = "./exampleRepo";
////        cloneRepo(uri, pathName);
////        List<Map<String, Object>> gitLogs = getGitLogs(pathName);
////        info(gitLogs);
////        switchBranch(pathName, "master");
////        debug(getStatus(pathName));
////        debug(getAllBranches(pathName));
////        debug(getGitLogs(pathName));
        info(commitSHA.length());
        getStatusByCommit(pathName, commitSHA);
    }
}
