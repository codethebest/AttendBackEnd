package com.liyolomoko.attendeventappfrontend.service.user.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.liyolomoko.attendeventappfrontend.conf.databases.App;
import com.liyolomoko.attendeventappfrontend.domain.user.CommentOnPost;
import com.liyolomoko.attendeventappfrontend.repositories.user.CommentOnPostRepository;
import com.liyolomoko.attendeventappfrontend.repositories.user.impl.CommentOnPostRepositoryImp;
import com.liyolomoko.attendeventappfrontend.service.user.CommentOnPostService;

import java.sql.SQLException;

public class CommentOnPostServiceimpl extends IntentService implements CommentOnPostService{

    private final CommentOnPostRepository repo;
    private static final String ACTION_POST = "com.liyolomoko.attendeventappfrontend.services.user.impl.action.POST";
    private static final String ACTION_EDIT = "com.example.leo.tpassignment61.services.user.impl.action.EDIT";

    // TODO: Rename parameters
    private static final String EXTRA_POST = "com.example.leo.tpassignment61.services.user.impl.extra.POST";
    private static final String EXTRA_EDIT = "com.example.leo.tpassignment61.services.user.impl.extra.EDIT";

    private static CommentOnPostServiceimpl service = null;
    public static CommentOnPostServiceimpl getInstance()
    {
        if(service == null)
            service = new CommentOnPostServiceimpl();
        return service;
    }
    public CommentOnPostServiceimpl() {
        super("CommentOnPostServiceimpl");
        repo = new CommentOnPostRepositoryImp(App.getAppContext());
    }

    public void postAnComment(Context context, CommentOnPost comment) {
        Intent intent = new Intent(context, CommentOnPostServiceimpl.class);
        intent.setAction(ACTION_POST);
        intent.putExtra(EXTRA_POST, comment);
        context.startService(intent);
    }

    public void editComment(Context context, CommentOnPost comment) {
        Intent intent = new Intent(context, CommentOnPostServiceimpl.class);
        intent.setAction(ACTION_POST);
        intent.putExtra(EXTRA_EDIT, comment);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_POST.equals(action)) {
                final CommentOnPost commentOnPost = (CommentOnPost)intent.getSerializableExtra(EXTRA_POST);
                postCommentOn(commentOnPost);
            } else if (ACTION_EDIT.equals(action)) {
                final CommentOnPost commentOnPost = (CommentOnPost)intent.getSerializableExtra(EXTRA_EDIT);
                editCommentOn(commentOnPost);
            }
        }
    }

    public void postCommentOn(CommentOnPost commentOnPost) {
        //POST and Save Local
        try {
            repo.save(commentOnPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void editCommentOn(CommentOnPost commentOnPost) {
        //POST and Save Local
        try {
            repo.save(commentOnPost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
