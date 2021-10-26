package com.example.graphqlspringboot.resolver;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient (ClientInput input){
        return clientRepository.save(getClient(input));
    }

    private Client getClient(ClientInput clientInput) {
        return Client.builder()
                .id(clientInput.getId())
                .firstName(clientInput.getFirstName())
                .lastName(clientInput.getLastName())
                .occupation(clientInput.getOccupation())
                .address(clientInput.getAddress())
                .build();
    }

    public Client updateClient(ClientInput clientInput){
        Client client = getClient(clientInput);
        Optional<Client>targetClient = clientRepository.findById(client.getId());
        if (targetClient.isPresent()){
            return clientRepository.save(client);
        }
        throw new GraphQLException("Client id " + client.getId() + " does not exist.");
    }
}
