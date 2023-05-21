<?php

namespace App\Exceptions;

use App\Utils\APIResponse;
use Illuminate\Foundation\Exceptions\Handler as ExceptionHandler;
use Symfony\Component\HttpKernel\Exception\NotFoundHttpException;
use Throwable;

class Handler extends ExceptionHandler {
    /**
     * The list of the inputs that are never flashed to the session on validation exceptions.
     *
     * @var array<int, string>
     */
    protected $dontFlash = [
        'current_password',
        'password',
        'password_confirmation',
    ];

    /**
     * Register the exception handling callbacks for the application.
     */
    public function register(): void {
        $this->reportable(function (Throwable $e) {
        });
    }

    public function render($request, Throwable $exception) {
        if ($exception instanceof NotFoundHttpException) {
            if ($request->is('api/*')) {
                return APIResponse::error('Não encontrado', 404);
            }
        } else if ($exception instanceof \Illuminate\Auth\AuthenticationException) {
            if ($request->is('api/*')) {
                return APIResponse::error('Não autenticado', 401);
            }
        } else {
            if ($request->is('api/*')) {
                return APIResponse::error($exception->getMessage(), $exception->getCode());
            }
        }

        return parent::render($request, $exception);
    }
}
