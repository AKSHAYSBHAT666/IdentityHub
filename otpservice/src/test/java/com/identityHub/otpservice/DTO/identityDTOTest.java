package com.identityHub.otpservice.DTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class identityDTOTest {

    @Test
    public void testGettersAndSetters() {
        identityDTO dto = new identityDTO();

        // Set values
        dto.setIdValue("ABC123");
        dto.setIdType("PAN");
        dto.setUsrId(101L);

        // Assert getters return the correct values
        assertEquals("ABC123", dto.getIdValue());
        assertEquals("PAN", dto.getIdType());
        assertEquals(101L, dto.getUsrId());
    }

    @Test
    public void testAllArgsConstructor() {
        identityDTO dto = new identityDTO("XYZ789", "AADHAR", 202L);

        assertEquals("XYZ789", dto.getIdValue());
        assertEquals("AADHAR", dto.getIdType());
        assertEquals(202L, dto.getUsrId());
    }
}
