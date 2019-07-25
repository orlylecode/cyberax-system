package com.cogitech.cyberax.web.rest;

import com.cogitech.cyberax.CyberaxSystemApp;
import com.cogitech.cyberax.domain.Config;
import com.cogitech.cyberax.repository.ConfigRepository;
import com.cogitech.cyberax.service.ConfigService;
import com.cogitech.cyberax.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cogitech.cyberax.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ConfigResource} REST controller.
 */
@SpringBootTest(classes = CyberaxSystemApp.class)
public class ConfigResourceIT {

    private static final Integer DEFAULT_MAX_MISE = 1;
    private static final Integer UPDATED_MAX_MISE = 2;

    private static final Integer DEFAULT_MAX_GAGNANT = 1;
    private static final Integer UPDATED_MAX_GAGNANT = 2;

    private static final String DEFAULT_URL_SERVER = "AAAAAAAAAA";
    private static final String UPDATED_URL_SERVER = "BBBBBBBBBB";

    private static final String DEFAULT_API_KEY = "AAAAAAAAAA";
    private static final String UPDATED_API_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_API_PASS = "AAAAAAAAAA";
    private static final String UPDATED_API_PASS = "BBBBBBBBBB";

    private static final Double DEFAULT_MONTANT_MISE = 1D;
    private static final Double UPDATED_MONTANT_MISE = 2D;

    private static final Double DEFAULT_GAINS_1 = 1D;
    private static final Double UPDATED_GAINS_1 = 2D;

    private static final Double DEFAULT_GAINS_2 = 1D;
    private static final Double UPDATED_GAINS_2 = 2D;

    private static final Double DEFAULT_GAINS_3 = 1D;
    private static final Double UPDATED_GAINS_3 = 2D;

    private static final Double DEFAULT_GAINS_4 = 1D;
    private static final Double UPDATED_GAINS_4 = 2D;

    private static final Double DEFAULT_GAINS_5 = 1D;
    private static final Double UPDATED_GAINS_5 = 2D;

    private static final Double DEFAULT_GAINS_6 = 1D;
    private static final Double UPDATED_GAINS_6 = 2D;

    private static final Double DEFAULT_GAINS_7 = 1D;
    private static final Double UPDATED_GAINS_7 = 2D;

    private static final Double DEFAULT_GAINS_8 = 1D;
    private static final Double UPDATED_GAINS_8 = 2D;

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private ConfigService configService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restConfigMockMvc;

    private Config config;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfigResource configResource = new ConfigResource(configService);
        this.restConfigMockMvc = MockMvcBuilders.standaloneSetup(configResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Config createEntity(EntityManager em) {
        Config config = new Config()
            .maxMise(DEFAULT_MAX_MISE)
            .maxGagnant(DEFAULT_MAX_GAGNANT)
            .urlServer(DEFAULT_URL_SERVER)
            .apiKey(DEFAULT_API_KEY)
            .apiPass(DEFAULT_API_PASS)
            .montantMise(DEFAULT_MONTANT_MISE)
            .gains1(DEFAULT_GAINS_1)
            .gains2(DEFAULT_GAINS_2)
            .gains3(DEFAULT_GAINS_3)
            .gains4(DEFAULT_GAINS_4)
            .gains5(DEFAULT_GAINS_5)
            .gains6(DEFAULT_GAINS_6)
            .gains7(DEFAULT_GAINS_7)
            .gains8(DEFAULT_GAINS_8);
        return config;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Config createUpdatedEntity(EntityManager em) {
        Config config = new Config()
            .maxMise(UPDATED_MAX_MISE)
            .maxGagnant(UPDATED_MAX_GAGNANT)
            .urlServer(UPDATED_URL_SERVER)
            .apiKey(UPDATED_API_KEY)
            .apiPass(UPDATED_API_PASS)
            .montantMise(UPDATED_MONTANT_MISE)
            .gains1(UPDATED_GAINS_1)
            .gains2(UPDATED_GAINS_2)
            .gains3(UPDATED_GAINS_3)
            .gains4(UPDATED_GAINS_4)
            .gains5(UPDATED_GAINS_5)
            .gains6(UPDATED_GAINS_6)
            .gains7(UPDATED_GAINS_7)
            .gains8(UPDATED_GAINS_8);
        return config;
    }

    @BeforeEach
    public void initTest() {
        config = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfig() throws Exception {
        int databaseSizeBeforeCreate = configRepository.findAll().size();

        // Create the Config
        restConfigMockMvc.perform(post("/api/configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(config)))
            .andExpect(status().isCreated());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeCreate + 1);
        Config testConfig = configList.get(configList.size() - 1);
        assertThat(testConfig.getMaxMise()).isEqualTo(DEFAULT_MAX_MISE);
        assertThat(testConfig.getMaxGagnant()).isEqualTo(DEFAULT_MAX_GAGNANT);
        assertThat(testConfig.getUrlServer()).isEqualTo(DEFAULT_URL_SERVER);
        assertThat(testConfig.getApiKey()).isEqualTo(DEFAULT_API_KEY);
        assertThat(testConfig.getApiPass()).isEqualTo(DEFAULT_API_PASS);
        assertThat(testConfig.getMontantMise()).isEqualTo(DEFAULT_MONTANT_MISE);
        assertThat(testConfig.getGains1()).isEqualTo(DEFAULT_GAINS_1);
        assertThat(testConfig.getGains2()).isEqualTo(DEFAULT_GAINS_2);
        assertThat(testConfig.getGains3()).isEqualTo(DEFAULT_GAINS_3);
        assertThat(testConfig.getGains4()).isEqualTo(DEFAULT_GAINS_4);
        assertThat(testConfig.getGains5()).isEqualTo(DEFAULT_GAINS_5);
        assertThat(testConfig.getGains6()).isEqualTo(DEFAULT_GAINS_6);
        assertThat(testConfig.getGains7()).isEqualTo(DEFAULT_GAINS_7);
        assertThat(testConfig.getGains8()).isEqualTo(DEFAULT_GAINS_8);
    }

    @Test
    @Transactional
    public void createConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configRepository.findAll().size();

        // Create the Config with an existing ID
        config.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigMockMvc.perform(post("/api/configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(config)))
            .andExpect(status().isBadRequest());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConfigs() throws Exception {
        // Initialize the database
        configRepository.saveAndFlush(config);

        // Get all the configList
        restConfigMockMvc.perform(get("/api/configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(config.getId().intValue())))
            .andExpect(jsonPath("$.[*].maxMise").value(hasItem(DEFAULT_MAX_MISE)))
            .andExpect(jsonPath("$.[*].maxGagnant").value(hasItem(DEFAULT_MAX_GAGNANT)))
            .andExpect(jsonPath("$.[*].urlServer").value(hasItem(DEFAULT_URL_SERVER.toString())))
            .andExpect(jsonPath("$.[*].apiKey").value(hasItem(DEFAULT_API_KEY.toString())))
            .andExpect(jsonPath("$.[*].apiPass").value(hasItem(DEFAULT_API_PASS.toString())))
            .andExpect(jsonPath("$.[*].montantMise").value(hasItem(DEFAULT_MONTANT_MISE.doubleValue())))
            .andExpect(jsonPath("$.[*].gains1").value(hasItem(DEFAULT_GAINS_1.doubleValue())))
            .andExpect(jsonPath("$.[*].gains2").value(hasItem(DEFAULT_GAINS_2.doubleValue())))
            .andExpect(jsonPath("$.[*].gains3").value(hasItem(DEFAULT_GAINS_3.doubleValue())))
            .andExpect(jsonPath("$.[*].gains4").value(hasItem(DEFAULT_GAINS_4.doubleValue())))
            .andExpect(jsonPath("$.[*].gains5").value(hasItem(DEFAULT_GAINS_5.doubleValue())))
            .andExpect(jsonPath("$.[*].gains6").value(hasItem(DEFAULT_GAINS_6.doubleValue())))
            .andExpect(jsonPath("$.[*].gains7").value(hasItem(DEFAULT_GAINS_7.doubleValue())))
            .andExpect(jsonPath("$.[*].gains8").value(hasItem(DEFAULT_GAINS_8.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getConfig() throws Exception {
        // Initialize the database
        configRepository.saveAndFlush(config);

        // Get the config
        restConfigMockMvc.perform(get("/api/configs/{id}", config.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(config.getId().intValue()))
            .andExpect(jsonPath("$.maxMise").value(DEFAULT_MAX_MISE))
            .andExpect(jsonPath("$.maxGagnant").value(DEFAULT_MAX_GAGNANT))
            .andExpect(jsonPath("$.urlServer").value(DEFAULT_URL_SERVER.toString()))
            .andExpect(jsonPath("$.apiKey").value(DEFAULT_API_KEY.toString()))
            .andExpect(jsonPath("$.apiPass").value(DEFAULT_API_PASS.toString()))
            .andExpect(jsonPath("$.montantMise").value(DEFAULT_MONTANT_MISE.doubleValue()))
            .andExpect(jsonPath("$.gains1").value(DEFAULT_GAINS_1.doubleValue()))
            .andExpect(jsonPath("$.gains2").value(DEFAULT_GAINS_2.doubleValue()))
            .andExpect(jsonPath("$.gains3").value(DEFAULT_GAINS_3.doubleValue()))
            .andExpect(jsonPath("$.gains4").value(DEFAULT_GAINS_4.doubleValue()))
            .andExpect(jsonPath("$.gains5").value(DEFAULT_GAINS_5.doubleValue()))
            .andExpect(jsonPath("$.gains6").value(DEFAULT_GAINS_6.doubleValue()))
            .andExpect(jsonPath("$.gains7").value(DEFAULT_GAINS_7.doubleValue()))
            .andExpect(jsonPath("$.gains8").value(DEFAULT_GAINS_8.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingConfig() throws Exception {
        // Get the config
        restConfigMockMvc.perform(get("/api/configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfig() throws Exception {
        // Initialize the database
        configService.save(config);

        int databaseSizeBeforeUpdate = configRepository.findAll().size();

        // Update the config
        Config updatedConfig = configRepository.findById(config.getId()).get();
        // Disconnect from session so that the updates on updatedConfig are not directly saved in db
        em.detach(updatedConfig);
        updatedConfig
            .maxMise(UPDATED_MAX_MISE)
            .maxGagnant(UPDATED_MAX_GAGNANT)
            .urlServer(UPDATED_URL_SERVER)
            .apiKey(UPDATED_API_KEY)
            .apiPass(UPDATED_API_PASS)
            .montantMise(UPDATED_MONTANT_MISE)
            .gains1(UPDATED_GAINS_1)
            .gains2(UPDATED_GAINS_2)
            .gains3(UPDATED_GAINS_3)
            .gains4(UPDATED_GAINS_4)
            .gains5(UPDATED_GAINS_5)
            .gains6(UPDATED_GAINS_6)
            .gains7(UPDATED_GAINS_7)
            .gains8(UPDATED_GAINS_8);

        restConfigMockMvc.perform(put("/api/configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConfig)))
            .andExpect(status().isOk());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeUpdate);
        Config testConfig = configList.get(configList.size() - 1);
        assertThat(testConfig.getMaxMise()).isEqualTo(UPDATED_MAX_MISE);
        assertThat(testConfig.getMaxGagnant()).isEqualTo(UPDATED_MAX_GAGNANT);
        assertThat(testConfig.getUrlServer()).isEqualTo(UPDATED_URL_SERVER);
        assertThat(testConfig.getApiKey()).isEqualTo(UPDATED_API_KEY);
        assertThat(testConfig.getApiPass()).isEqualTo(UPDATED_API_PASS);
        assertThat(testConfig.getMontantMise()).isEqualTo(UPDATED_MONTANT_MISE);
        assertThat(testConfig.getGains1()).isEqualTo(UPDATED_GAINS_1);
        assertThat(testConfig.getGains2()).isEqualTo(UPDATED_GAINS_2);
        assertThat(testConfig.getGains3()).isEqualTo(UPDATED_GAINS_3);
        assertThat(testConfig.getGains4()).isEqualTo(UPDATED_GAINS_4);
        assertThat(testConfig.getGains5()).isEqualTo(UPDATED_GAINS_5);
        assertThat(testConfig.getGains6()).isEqualTo(UPDATED_GAINS_6);
        assertThat(testConfig.getGains7()).isEqualTo(UPDATED_GAINS_7);
        assertThat(testConfig.getGains8()).isEqualTo(UPDATED_GAINS_8);
    }

    @Test
    @Transactional
    public void updateNonExistingConfig() throws Exception {
        int databaseSizeBeforeUpdate = configRepository.findAll().size();

        // Create the Config

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfigMockMvc.perform(put("/api/configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(config)))
            .andExpect(status().isBadRequest());

        // Validate the Config in the database
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfig() throws Exception {
        // Initialize the database
        configService.save(config);

        int databaseSizeBeforeDelete = configRepository.findAll().size();

        // Delete the config
        restConfigMockMvc.perform(delete("/api/configs/{id}", config.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Config> configList = configRepository.findAll();
        assertThat(configList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Config.class);
        Config config1 = new Config();
        config1.setId(1L);
        Config config2 = new Config();
        config2.setId(config1.getId());
        assertThat(config1).isEqualTo(config2);
        config2.setId(2L);
        assertThat(config1).isNotEqualTo(config2);
        config1.setId(null);
        assertThat(config1).isNotEqualTo(config2);
    }
}
