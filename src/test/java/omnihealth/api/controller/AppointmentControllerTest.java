package omnihealth.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import omnihealth.api.domain.appointment.AppointmentDetailData;
import omnihealth.api.domain.appointment.AppointmentSchedulingData;
import omnihealth.api.domain.appointment.AppointmentService;
import omnihealth.api.domain.doctor.Specialty;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<AppointmentSchedulingData> appointmentSchedulingDataJson;

	@Autowired
	private JacksonTester<AppointmentDetailData> appointmentDetailDataJson;

	@MockBean
	private AppointmentService appointmentService;

	@Test
	@DisplayName("should return http code 400 when information is invalid")
	@WithMockUser
	void testToSchedule_scene1() throws Exception {
		var response = mvc.perform(post("/appointments")).andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	@DisplayName("should return http code 200 when information is valid")
	@WithMockUser
	void testToSchedule_scene2() throws Exception {
		var date = LocalDateTime.now().plusHours(1);
		var specialty = Specialty.CARDIOLOGY;

		var detailData = new AppointmentDetailData(null, 2l, 5l, date);
		when(appointmentService.toSchedule(any())).thenReturn(detailData);

		var response = mvc
				.perform(post("/appointments").contentType(MediaType.APPLICATION_JSON)
						.content(appointmentSchedulingDataJson
								.write(new AppointmentSchedulingData(2l, 5l, date, specialty)).getJson()))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		var jsonExpected = appointmentDetailDataJson.write(detailData).getJson();

		assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
	}

}
