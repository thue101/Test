package com.example.graphqlspringboot.resolver;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientResolver implements GraphQLResolver {

    @Autowired
    private ClientRepository clientRepository;

    public String clients(Client client){
        Optional<Client> fetchClient = clientRepository.findById(client.getId());
        if (fetchClient.isPresent()){
            return fetchClient.get().getFirstName();
        }
        throw new GraphQLException("Client not found");
    }

}
