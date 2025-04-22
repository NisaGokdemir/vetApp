package org.codewhiskers.vetapp.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*
🔧 1. Hata: GenericService.java Could not autowire. No beans of 'R' type found.
🎯 Neden Olur?
Java generic'leri çalışma zamanında type erasure nedeniyle R tipi hakkında bilgi kaybolur. Spring ise @Autowired sırasında o tipin ne olduğunu bilemez ve bean olarak inject etmeye çalıştığında başarısız olur.

✅ Çözüm: Constructor'da doğrudan repository inject edilip super() ile gönderilmeli.
Zaten sen SpeciesServiceImpl içinde bu işlemi super(speciesRepository) diyerek yapmışsın, bu doğru.

Ancak IDE hata veriyorsa muhtemelen GenericService'in kendisi bir Spring Bean gibi kullanılmaya çalışılıyor.

🛠️ Yapman gerekenler:
GenericService sınıfına kesinlikle @Service veya @Component koyma.

GenericService soyut (abstract) bir sınıf olarak kalmalı. Sadece alt sınıflar üzerinden çağrılmalı.

Eğer hâlâ hata varsa IDE'ye "bu sınıf generic, Spring tarafından doğrudan inject edilmeyecek" olduğunu anlatmak için @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") kullanabilirsin:
* */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public abstract class GenericService<T, ID, R extends JpaRepository<T, ID>> {

    protected final R repository;

    @Autowired
    public GenericService(R repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    public Page<T> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T update(ID id, T entity) {
        if (repository.existsById(id)) {
            return repository.save(entity);
        } else {
            throw new IllegalArgumentException("Entity not found");
        }
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }
}