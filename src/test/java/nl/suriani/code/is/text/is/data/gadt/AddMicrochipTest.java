package nl.suriani.code.is.text.is.data.gadt;

import nl.suriani.code.is.text.is.data.application.command.AddMicrochipCommand;
import nl.suriani.code.is.text.is.data.application.command.Dispatcher;
import nl.suriani.code.is.text.is.data.model.Microchip;
import nl.suriani.code.is.text.is.data.model.MicrochipId;
import nl.suriani.code.is.text.is.data.model.PetDoor;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddMicrochipTest {

    @Mock
    private PetDoorRepository petDoorRepository;

    @InjectMocks
    private Dispatcher dispatcher;

    @Captor
    private ArgumentCaptor<PetDoor> petDoorArgumentCaptor;

    @Test
    void testAddMicrochip() {
        var command = new AddMicrochipCommand(
                UUID.randomUUID(),
                "Cakey",
                PetDoor.DEFAULT_REGISTERED_CATS_MODE_ID
        );

        when(petDoorRepository.fetch()).thenReturn(new PetDoor());


        dispatcher.dispatch(command);

        verify(petDoorRepository, times(1))
                .save(petDoorArgumentCaptor.capture());

        var petDoor = petDoorArgumentCaptor.getValue();

        assertThat(petDoor.microchips().getFirst()).isEqualTo(new Microchip(
                new MicrochipId(command.idMicrochip()),
                command.namePet(),
                command.activeMode()));
    }
}