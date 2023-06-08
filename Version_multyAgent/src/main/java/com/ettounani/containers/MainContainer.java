package com.ettounani.containers;

import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ControllerException;
public class MainContainer {
    public static void main(String[] args) throws ControllerException {
        Runtime runtime=Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(Profile.GUI,"true");
        AgentContainer mainContainer=runtime.createMainContainer(profile);
        mainContainer.start();
    }
}
/*
Coded with love by Abderrahmane Ettounani
 */
