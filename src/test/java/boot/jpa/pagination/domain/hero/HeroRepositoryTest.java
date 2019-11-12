package boot.jpa.pagination.domain.hero;

import boot.jpa.pagination.dto.HeroFindAllResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroRepositoryTest {

    @Autowired
    private HeroRepository heroRepository;

    @Test
    public void cleanUp() {
        heroRepository.deleteAll();
    }

    @Test
    public void Pagination() {
        //given
        IntStream.rangeClosed(1, 10).forEach(i ->
                heroRepository.save(Hero.builder()
                        .name("github.com/hong-il")
                        .age(26)
                        .note("github.com/hong-il")
                        .build()));

        Pageable pageable = PageRequest.of(1, 5);

        //when
        Page<HeroFindAllResponseDto> page = heroRepository.HeroFindAllResponse(pageable);

        //then
    }
}
