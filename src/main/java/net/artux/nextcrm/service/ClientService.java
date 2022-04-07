package net.artux.nextcrm.service;

import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientService extends AbstractService<ClientEntity, ClientEntity, ClientEntity, ClientRepository> {

    ClientService(ClientRepository repository){
        super(repository);
    }

    @Override
    public ClientEntity create(ClientEntity dto) {
        repository.save(dto);
        return dto;
    }

    @Override
    public ClientEntity update(Long id, ClientEntity dto) {
        return null;
    }

    @Override
    public ClientEntity read(Long id) {
        return findById(id);
    }

    public ClientEntity findById(Long id) {
        return findById(id, "Клиент не найден");
    }

    public List<ClientEntity> readAll() {
        return repository.findAll();
    }
}
