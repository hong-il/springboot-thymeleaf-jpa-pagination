package boot.jpa.pagination.dto;

import boot.jpa.pagination.domain.hero.Hero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeroUpdateRequestDto {

    private Long id;
    private String name;
    private int age;
    private String note;

    public Hero toEntity(){
        return Hero.builder()
                .id(id)
                .name(name)
                .age(age)
                .note(note)
                .build();
    }
}
