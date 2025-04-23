package org.codewhiskers.vetapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.codewhiskers.vetapp.entity.RefreshToken;
import org.codewhiskers.vetapp.entity.User;
import org.codewhiskers.vetapp.exception.BaseException;
import org.codewhiskers.vetapp.exception.ErrorMessage;
import org.codewhiskers.vetapp.exception.MessageType;
import org.codewhiskers.vetapp.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(Date.from(Instant.now().plusSeconds(60 * 60 * 24 * 7))); // 7 gün geçerli

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpireDate().before(new Date())) {
            refreshTokenRepository.delete(token);
            throw new BaseException(
                    new ErrorMessage(MessageType.REFRESH_TOKEN_EXPIRED, token.getRefreshToken())
            );
        }
        return token;
    }

    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, token)
                ));
    }
}
