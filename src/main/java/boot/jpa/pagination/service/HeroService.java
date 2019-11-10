package boot.jpa.pagination.service;

import boot.jpa.pagination.domain.hero.Hero;
import boot.jpa.pagination.domain.hero.HeroRepository;
import boot.jpa.pagination.dto.HeroFindAllResponseDto;
import boot.jpa.pagination.dto.HeroFindByIdResponseDto;
import boot.jpa.pagination.dto.HeroSaveRequestDto;
import boot.jpa.pagination.dto.HeroUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HeroService {

    private HeroRepository heroRepository;

    @Transactional
    public Long HeroSaveRequest(HeroSaveRequestDto dto){
        return heroRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    @ReadOnlyProperty
    public List<HeroFindAllResponseDto> HeroFindAllResponse(){
        return heroRepository.findAll().stream()
                .map(HeroFindAllResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public HeroFindByIdResponseDto HeroFindByIdResponse(Long id) {
        Hero hero = heroRepository.findById(id).orElse(null);
        return new HeroFindByIdResponseDto(hero);
    }

    @Transactional
    public Long HeroUpdateRequest(HeroUpdateRequestDto dto){
        return heroRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public void HeroDeleteByIdRequest(Long id) { heroRepository.deleteById(id); }
}
