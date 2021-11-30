package com.IW.interfaces;

import com.IW.interfaces.IBeans.IBook;

public interface SQL extends IDAO{
    interface IAuthorDAO extends SQL{
        void addBook(IBook book);
        void removeBook(IBook book);
        boolean checkUser();
    }
    interface IBookDAO extends SQL{

    }
    interface IChapterDAO extends SQL{

    }
    interface ICharacterDAO extends SQL{

    }
    interface ISceneDAO extends SQL{

    }
    interface IPartDAO extends SQL{

    }
}
