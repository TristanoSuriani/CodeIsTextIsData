package nl.suriani.code.is.text.is.data.application.usecase.remove_microchip;

import nl.suriani.code.is.text.is.data.adt.Environment;
import nl.suriani.code.is.text.is.data.application.command.AddMicrochipCommand;
import nl.suriani.code.is.text.is.data.application.command.RemoveMicrochipCommand;
import nl.suriani.code.is.text.is.data.application.usecase.add_microchip.AddMicroChipUseCase;
import nl.suriani.code.is.text.is.data.model.Microchip;
import nl.suriani.code.is.text.is.data.model.PetDoor;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveMicrochipUseCaseTest {
    @Mock
    private PetDoorRepository petDoorRepository;

    @Mock
    private Supplier<RemoveMicrochipCommand> commandSupplier;

    @InjectMocks
    private RemoveMicrochipUseCase useCase;

    @Captor
    private ArgumentCaptor<PetDoor> petDoorArgumentCaptor;

    @Test
    void testRemoveMicrochip() {
        var command = new RemoveMicrochipCommand(
                UUID.randomUUID()
        );
        var environment = new Environment();

        when(commandSupplier.get()).thenReturn(command);
        when(petDoorRepository.fetch()).thenReturn(new PetDoor());

        var definition = useCase.define();
        assertThat(definition.explain())
                .isEqualTo("(mapTo (SavePetDoor (RemoveMicrochip (FetchPetDoor) (Literal UUID))) mapper)");
        definition.eval(environment);

        verify(petDoorRepository, times(1))
                .save(petDoorArgumentCaptor.capture());

        var petDoor = petDoorArgumentCaptor.getValue();

    }
}