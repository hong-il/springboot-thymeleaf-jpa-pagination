package boot.jpa.pagination.service;

import boot.jpa.pagination.domain.hero.Hero;
import boot.jpa.pagination.domain.hero.HeroRepository;
import boot.jpa.pagination.dto.HeroFindAllResponseDto;
import boot.jpa.pagination.dto.HeroFindByIdResponseDto;
import boot.jpa.pagination.dto.HeroSaveRequestDto;
import boot.jpa.pagination.dto.HeroUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public Page<HeroFindAllResponseDto> HeroFindAllResponse(Pageable pageable){
        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1;
        pageable = PageRequest.of(page, 5);
        return heroRepository.HeroFindAllResponse(pageable);
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
