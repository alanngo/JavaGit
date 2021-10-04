package com.example.util;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.*;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevObject;
import org.eclipse.jgit.revwalk.RevTag;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.File;
import java.util.*;

import static com.example.logger.MyLogger.*;

public class GitCommands
{
    // clone
    public static void cloneRepo(String uri, String pathName)
    {
        try(Git git = Git.cloneRepository()
                .setURI(uri)
                .setDirectory(new File(pathName)).
                call())
        {
            info("cloning repo from "+uri);
            debug(git);
        }
        catch(Exception e) {error(e);}
    }

    // checkout -b
    public static void createBranch(String pathName, String newBranch)
    {
        try(Git git = Git.open(new File(pathName)))
        {
            git
                    .checkout()
                    .setCreateBranch(true)
                    .setName(newBranch)
                    .call();
            info("successfully created branch "+newBranch);
        }
        catch(Exception e){error(e);}
    }

    public static void switchBranch(String pathName, String newBranch)
    {
        try(Git git = Git.open(new File(pathName)))
        {
            git
                    .checkout()
                    .setCreateBranch(false)
                    .setName(newBranch)
                    .call();
            info("switched to branch "+newBranch);
        }
        catch(Exception e){error(e);}
    }

    // TODO: need to unwrap
    public static List<Map<String, Object>> getGitLogs(String pathName)
    {
        List<Map<String, Object>> ret = new ArrayList<>();
        try(Git git = Git.open(new File(pathName)))
        {
            Iterable<RevCommit> gitLogs = git.log().call();
            gitLogs.forEach(e ->
            {
                Map<String, Object> commitInfo = new HashMap<>();
                commitInfo.put("fullMessage", e.getFullMessage());
                commitInfo.put("tree", e.getTree());
                commitInfo.put("commitTime", e.getCommitTime());
                commitInfo.put("name", e.getName());
                ret.add(commitInfo);
            });
        }
        catch (Exception e) {error(e);}
        return ret;
    }

    // get all branches
    public static List<String> getAllBranches(String pathName)
    {
        List<String> ret = new ArrayList<>();
        try(Git git = Git.open(new File(pathName)))
        {
            List<Ref> branches = git.branchList().setListMode(ListMode.ALL).call();
            branches.forEach(b -> ret.add(b.getName()));
        }
        catch(Exception e){error(e);}
        return ret;
    }

    // check status
    public static Map<String, Set<String>> getStatus(String pathName)
    {
        Map<String, Set<String>> ret = new HashMap<>();
        try(Git git = Git.open(new File(pathName)))
        {
            Status status = git.status().call();
            ret.put("modifedFiles", status.getModified());
            ret.put("addedFiles", status.getAdded());
            ret.put("changedFiles", status.getChanged());
            ret.put("removedFiles", status.getRemoved());

            // add more k-v pairs accordingly
        }
        catch (Exception e) {error(e);}
        return ret;
    }

    // TODO: replicate git show command
    public static void getStatusByCommit(String pathName, String sha)
    {
        try(Git git = Git.open(new File(pathName)))
        {
            Repository repo = git.getRepository();
            ObjectId objectId = ObjectId.fromString(sha);
            RevWalk revWalk = new RevWalk(repo);
            RevObject revTag =  revWalk.parseAny(objectId);

        }
        catch (Exception e) {error(e);}
    }
}
