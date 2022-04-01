package net.artux.nextcrm.service;

import net.artux.nextcrm.model.client.ClientDto;
import net.artux.nextcrm.model.client.ClientEntity;
import net.artux.nextcrm.model.client.ClientMapper;
import net.artux.nextcrm.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientService extends AbstractService<ClientEntity, ClientDto, ClientDto, ClientRepository> {

    private final ClientMapper mapper;

    ClientService(ClientRepository repository, ClientMapper mapper){
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public ClientDto create(ClientDto dto) {
        repository.save(mapper.from(dto));
        return dto;
    }

    @Override
    public ClientDto update(Long id, ClientDto dto) {
        return null;
    }

    @Override
    public ClientDto read(Long id) {
        return mapper.dto(findById(id));
    }

    public ClientEntity findById(Long id) {
        return findById(id, "Клиент не найден");
    }

    public List<ClientDto> readAll() {
        return mapper.listDto(repository.findAll());
    }
}
