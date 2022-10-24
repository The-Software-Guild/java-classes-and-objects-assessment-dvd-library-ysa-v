/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sal.dvdlibrary.controller;

import com.sal.dvdlibrary.dao.DvdLibraryDaoException;
import com.sal.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.sal.dvdlibrary.dao.dvdLibraryDao;
import com.sal.dvdlibrary.dto.DvD;
import com.sal.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author salajrawi
 */
public class DvdLibraryController {

    private DvdLibraryView view;
    private dvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryView view, dvdLibraryDao dao) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
        exitMessage();
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    /*
     Method in the Controller to orchestrate the creation of a new student. Our method will do the following:
     Display the Create dvd banner
     Get all the dvd data from the user and create the new dvd object
     Store the new dvd object
    Display the Create dvd Success banner
     */
    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvDBanner();
        DvD newDvd = view.getNewDvDInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    /*
     a method called listDvds that will get a list of all Dvd objects in
    the system from the DAO and then hand that list to the view to display to the user.
     */
    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List(DvD) dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    /*
    This method asks the view to display the View dvd banner and get the titlr from the user
     */
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        DvD dvd = dao.getDvd(dvdTitle);
    }

    /*
    This method will ask the view to display the Remove dvd banner and ask the user for the title of the dvd to be removed
     */
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        DvD removedDvd = dao.removeDvd(dvdTitle);
        view.displayRemoveResult(removedDvd);
    }

    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        String title = view.getDvdTitleChoice();
        DvD currentDVD = dao.getDvd(title);
        if (currentDVD == null) {
            view.displayNullDVD();
        } else {
            view.displayDvd(currentDVD);
            int editMenuSelection = 0;
            boolean keepGoing = true;
            while (keepGoing) {
                editMenuSelection = view.printEditMenuAndGetSelection();

                switch (editMenuSelection) {
                    case 1:
                        editReleaseDate(title);
                        break;
                    case 2:
                        editMPAA(title);
                        break;
                    case 3:
                        editDirectorName(title);
                        break;
                    case 4:
                        editUserRating(title);
                        break;
                    case 5:
                        editStudioName(title);
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                if (keepGoing == false) {
                    break;
                }
            }
        }
    }
    private int getEditMenuSelection(){
        return view.printEditMenuAndGetSelection();
    }
    private void editReleaseDate(String title) throws DvdLibraryDaoException {
        view.displayEditReleaseDateBanner();
        String newReleaseDate = view.getNewReleaseDate();
        dao.editReleaseDate(title, newReleaseDate);
        view.displayEditDvdSuccess();
    }
    private void editMPAA(String title) throws DvdLibraryDaoException {
        view.displayEditMpaaBanner();
        String newMpaaRating = view.getNewMpaaRating();
        dao.editMPAA(title, newMpaaRating);
        view.displayEditDvdSuccess();
    }
    private void editDirectorName(String title) throws DvdLibraryDaoException {
        view.displayEditDirectorNameBanner();
        String newDirectorName = view.getNewDirectorName();
        dao.editDirectorName(title, newDirectorName);
        view.displayEditDvdSuccess();
    }
    private void editUserRating(String title) throws DvdLibraryDaoException {
        view.displayEditUserRating();
        String newUserRating = view.getNewUserRating();
        dao.editUserRating(title, newUserRating);
        view.displayEditDvdSuccess();
    }
    private void editStudioName(String title) throws DvdLibraryDaoException {
        view.displayEditStudio();
        String newStudioName = view.getNewStudio();
        dao.editStudio(title, newStudioName);
        view.displayEditDvdSuccess();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}