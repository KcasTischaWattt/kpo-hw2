package ru.hse.jade.sample.agents;

import jade.core.AID;
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
import ru.hse.jade.sample.model.Pair;

import java.util.*;

@JadeAgent
public class CustomerAgent extends Agent {
    private final String visitorName;
    private int time;
    private int[] actualMenu;
    private List<Pair<Integer, Integer>> orders = new ArrayList<>();
    private int orderIndex = 0;
    private AID supervisor;
    public CustomerAgent(String visitorName, int time) {
        this.visitorName = visitorName;
        this.time = time;
    }
    @Override
    protected void setup() {
        System.out.println("I am alive!");
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Serve-client");
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, template);
            supervisor = result[0].getName();
        }
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
        this.addBehaviour(new MenuRequest());
    }
    @Override
    protected void takeDown() {
        System.out.println("I am dead!");
    }
    private class MenuRequest extends Behaviour {
        private int step = 0;
        private MessageTemplate mt;
        @Override
        public void action() {
            switch (step) {
                case 0: {
                    ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
                    req.addReceiver(supervisor);
                    req.setConversationId("menu-creating");
                    req.setReplyWith("req" + System.currentTimeMillis());
                    myAgent.send(req);
                    mt = MessageTemplate.and(MessageTemplate.MatchConversationId("menu-creating"),
                            MessageTemplate.MatchInReplyTo(req.getReplyWith()));
                    step++;
                }
                    break;
                case 1: {
                    ACLMessage reply = myAgent.receive(mt);
                    if (reply != null) {
                        actualMenu = Arrays.stream(reply.getContent().split(" ")).mapToInt(Integer::parseInt).toArray();
                    } else {
                        block();
                    }
                    DeserializeOrders();
                    step++;
                }
                    break;
                case 2: {
                    boolean flag = false;
                    for (var dish : actualMenu) {
                        if (dish == orders.get(orderIndex).getSecond()) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        ACLMessage req = new ACLMessage(ACLMessage.REQUEST);
                        req.addReceiver(supervisor);
                        req.setContent(orders.get(orderIndex).toString() + time);
                        req.setConversationId("create-order");
                        req.setReplyWith("req" + System.currentTimeMillis());
                        myAgent.send(req);
                        mt = MessageTemplate.and(MessageTemplate.MatchConversationId("menu-creating"),
                                MessageTemplate.MatchInReplyTo(req.getReplyWith()));
                    }
                    orderIndex++;

                    if (orderIndex == orders.size()) {
                        step++;
                    }
                }
                    break;
            }
        }

        @Override
        public boolean done() {
            return step == 3;
        }
    }
    private void DeserializeOrders() {
        // здесь могла быть ваша  ̶р̶е̶к̶л̶а̶м̶а̶ десириализация
    }
}
