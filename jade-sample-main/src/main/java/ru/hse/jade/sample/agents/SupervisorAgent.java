package ru.hse.jade.sample.agents;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ru.hse.jade.sample.configuration.JadeAgent;

@JadeAgent
public class SupervisorAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println("I am alive!");
    }
    @Override
    protected void takeDown() {
        System.out.println("I am dead!");
    }
}
