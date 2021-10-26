package com.example.graphqlspringboot.resolver;

import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    @Autowired
    private ClientRepository clientRepository;

    public Client client(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent())
            return client.get();
        throw new GraphQLException("Client does not exist.");
    }
    public List<Client> clients(){
        return (List<Client>) clientRepository.findAll();
    }
}
